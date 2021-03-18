package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.common.utils.CommonUtil;
import com.o0u0o.missyou.common.utils.OrderUtil;
import com.o0u0o.missyou.core.enumeration.OrderStatus;
import com.o0u0o.missyou.core.exception.http.ForbiddenException;
import com.o0u0o.missyou.core.exception.http.NotFoundException;
import com.o0u0o.missyou.core.exception.http.ParameterException;
import com.o0u0o.missyou.core.money.IMoneyDiscount;
import com.o0u0o.missyou.dto.OrderDTO;
import com.o0u0o.missyou.dto.SkuInfoDTO;
import com.o0u0o.missyou.logic.CouponChecker;
import com.o0u0o.missyou.logic.OrderChecker;
import com.o0u0o.missyou.model.*;
import com.o0u0o.missyou.repository.CouponRepository;
import com.o0u0o.missyou.repository.OrderRepository;
import com.o0u0o.missyou.repository.SkuRepository;
import com.o0u0o.missyou.repository.UserCouponRepository;
import com.o0u0o.missyou.service.OrderService;
import com.o0u0o.missyou.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OrderServiceImpl
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/9 3:43 下午
 * @Descripton: 订单业务实现类
 * @Version: v0.0.1
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SkuService skuService;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private IMoneyDiscount iMoneyDiscount;

    @Value("${missyou.order.max-sku-limit}")
    private int maxSkuLimit;

    @Value("${missyou.order.pay-time-limit}")
    private int payTimeLimit;


    @Override
    public OrderChecker isOk(Long uid, OrderDTO orderDTO){
        //前端计算的总的实际支付价格小于或等于0
        if (orderDTO.getFinalTotalPrice().compareTo(new BigDecimal("0")) <= 0){
            throw new ParameterException(50011);
        }

        //提取出sku的id集合，方便后续查库
        List<Long> sukIdList = orderDTO.getSkuInfoList()
                .stream()
                .map(SkuInfoDTO::getId)
                .collect(Collectors.toList());

        //查询sku信息列表
        List<Sku> skuList = skuService.getSkuListByIds(sukIdList);

        //订单校验 OrderChecker
        //优惠券校验 CouponChecker
        Long couponId = orderDTO.getCouponId();
        CouponChecker couponChecker = null;
        if (couponId != null){
            Coupon coupon = this.couponRepository.findById(couponId)
                    .orElseThrow(()-> new NotFoundException(40004));
            UserCoupon userCoupon = this.userCouponRepository.findFirstByUserIdAndCouponIdAndStatus(uid, couponId, 1)
                    .orElseThrow(()->new NotFoundException(50006));
            couponChecker = new CouponChecker(coupon, iMoneyDiscount);
        }

        //校验订单
        OrderChecker orderChecker = new OrderChecker(orderDTO, skuList, couponChecker, this.maxSkuLimit);
        orderChecker.isOk();
        return orderChecker;
    }


    /**
     * 下订单
     * @param uid 用户id
     * @param orderDTO 订单DTO
     * @param orderChecker
     * @return
     */
    @Transactional
    @Override
    public Long placeOrder(Long uid, OrderDTO orderDTO, OrderChecker orderChecker) {
        //1、创建订单
        //1.1  生成随机订单号
        String orderNo = OrderUtil.makeOrderNo();
        Calendar now = Calendar.getInstance();
        Calendar nowClone = (Calendar)now.clone();
        //订单过期时间
        Date expiredTime = CommonUtil.addSomeSeconds(now, this.payTimeLimit).getTime();

        Order order = Order.builder()
                .orderNo(orderNo)
                .totalPrice(orderDTO.getTotalPrice())
                .finalTotalPrice(orderDTO.getFinalTotalPrice())
                .userId(uid)
                .totalCount(orderChecker.getTotalCount().longValue())
                .snapImg(orderChecker.getLeaderImg())
                .snapTitle(orderChecker.getLeaderTitle())
                .status(OrderStatus.UNPAID.value())
                .expiredTime(expiredTime)
                .placedTime(nowClone.getTime())
                .build();
        order.setSnapAddress(orderDTO.getAddress());
        order.setSnapItems(orderChecker.getOrderSkuList());

        //1.2 写入数据库
        this.orderRepository.save(order);

        //2、减库存 reduceStock
        reduceStock(orderChecker);

        //3、如果有使用优惠券，则核销优惠券
        if (orderDTO.getCouponId() != null){
            writeOffCoupon(orderDTO.getCouponId(), order.getId(), uid);
        }

        //4、数据加入到延迟消息队列（通知优惠券和商品库存的归还）

        return order.getId();
    }

    /**
     * 减库存（乐观锁思想）
     * @param orderChecker
     */
    private void reduceStock(OrderChecker orderChecker){
        List<OrderSku> orderSkuList = orderChecker.getOrderSkuList();
        //遍历对每个sku进行减库存
        for (OrderSku orderSku : orderSkuList) {
            //防止扣库存出现负数情况
            int result = this.skuRepository.reduceStock(orderSku.getId(), orderSku.getCount().longValue());
            if (result  != 1){
                throw new ParameterException(50003);
            }
        }
    }

    /**
     * 核销优惠券
     * @param couponId 优惠券ID
     * @param oid 订单id
     * @param uid 用户id
     */
    private void writeOffCoupon(Long couponId, Long oid, Long uid){
        int result = this.userCouponRepository.writeOff(couponId, oid, uid);
        if (result != 1){
            throw new ForbiddenException(40012);
        }
    }


}

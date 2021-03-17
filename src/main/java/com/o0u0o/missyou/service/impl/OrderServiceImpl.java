package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.core.exception.http.NotFoundException;
import com.o0u0o.missyou.core.exception.http.ParameterException;
import com.o0u0o.missyou.core.money.IMoneyDiscount;
import com.o0u0o.missyou.dto.OrderDTO;
import com.o0u0o.missyou.dto.SkuInfoDTO;
import com.o0u0o.missyou.logic.CouponChecker;
import com.o0u0o.missyou.model.Coupon;
import com.o0u0o.missyou.model.Sku;
import com.o0u0o.missyou.model.UserCoupon;
import com.o0u0o.missyou.repository.CouponRepository;
import com.o0u0o.missyou.repository.UserCouponRepository;
import com.o0u0o.missyou.service.OrderService;
import com.o0u0o.missyou.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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
    private IMoneyDiscount iMoneyDiscount;

    @Override
    public void isOk(Long uid, OrderDTO orderDTO){
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
            UserCoupon userCoupon = this.userCouponRepository.findFirstByUserIdAndCouponId(uid, couponId)
                    .orElseThrow(()->new NotFoundException(50006));
            couponChecker = new CouponChecker(coupon, iMoneyDiscount);
        }

    }
}

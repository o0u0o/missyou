package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.bo.OrderMessageBO;
import com.o0u0o.missyou.core.enumeration.OrderStatus;
import com.o0u0o.missyou.core.exception.http.ServerErrorException;
import com.o0u0o.missyou.model.Order;
import com.o0u0o.missyou.repository.OrderRepository;
import com.o0u0o.missyou.repository.UserCouponRepository;
import com.o0u0o.missyou.service.CouponBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 对优惠券进行归还业务
 * @author o0u0o
 * @date 2021/5/20 9:51 下午
 */
@Service
public class CouponBackServiceImpl implements CouponBackService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

    /**
     * 归还优惠券
     * @param bo
     */
    @Override
    public void returnBack(OrderMessageBO bo) {
        Long couponId = bo.getCouponId();
        Long uid = bo.getUserId();
        Long orderId = bo.getOrderId();

        //没有使用优惠券，就无需归还
        if (couponId == -1){
            return;
        }

        //使用了优惠券，就需进行归还操作
        Optional<Order> optional = orderRepository.findFirstByUserIdAndId(uid, orderId);
        Order order = optional.orElseThrow(() -> {
            throw new ServerErrorException(9999);
        });

        //如果订单状态为为支付，或者为取消，则可以退回优惠券
        if (order.getStatusEnum().equals(OrderStatus.UNPAID) ||
            order.getStatusEnum().equals(OrderStatus.CANCELED)){
            this.userCouponRepository.returnBack(couponId, uid);
        }
    }
}

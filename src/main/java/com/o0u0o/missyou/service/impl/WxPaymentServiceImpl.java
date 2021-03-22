package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.core.LocalUser;
import com.o0u0o.missyou.core.exception.http.NotFoundException;
import com.o0u0o.missyou.model.Order;
import com.o0u0o.missyou.repository.OrderRepository;
import com.o0u0o.missyou.service.WxPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @ClassName WxPaymentServiceImpl
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/19 5:28 下午
 * @Descripton: 微信支付业务实现类
 * @Version: v0.0.1
 **/
@Service
public class WxPaymentServiceImpl implements WxPaymentService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * 预订单业务
     * 1、一气呵成，调用placeOrder 用户立即支付 -> 订单未过期
     * 2、历史订单，调用placeOrder 用户再支付 -> 订单可能未过期，也可能已过期
     * @param oid 订单号
     * @return
     */
    @Override
    public Map<String, String> preOrder(Long oid) {
        //1、查询订单号是否存在
        Long uid = LocalUser.getUser().getId();
        Optional<Order> optionalOrder = this.orderRepository.findFirstByUserIdAndId(uid, oid);
        Order order = optionalOrder.orElseThrow(() -> new NotFoundException(50009));
        
        return null;
    }
}

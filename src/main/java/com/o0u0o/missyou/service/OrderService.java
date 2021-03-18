package com.o0u0o.missyou.service;


import com.o0u0o.missyou.dto.OrderDTO;
import com.o0u0o.missyou.logic.CouponChecker;
import com.o0u0o.missyou.logic.OrderChecker;

/**
 * @ClassName OrderService
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/9 3:42 下午
 * @Descripton: 订单业务接口
 * @Version: v0.0.1
 **/
public interface OrderService {

    /**
     * 订单检查是否OK
     * @param uid 用户id
     * @param orderDTO 订单DTO
     * @return
     */
    public OrderChecker isOk(Long uid, OrderDTO orderDTO);

    /**
     * 下单
     * @param uid
     * @param orderDTO
     * @param couponChecker
     * @return
     */
    public Long placeOrder(Long uid, OrderDTO orderDTO, CouponChecker couponChecker);

}

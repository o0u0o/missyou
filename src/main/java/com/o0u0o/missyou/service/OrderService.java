package com.o0u0o.missyou.service;


import com.o0u0o.missyou.dto.OrderDTO;

/**
 * @ClassName OrderService
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/9 3:42 下午
 * @Descripton: 订单业务接口
 * @Version: v0.0.1
 **/
public interface OrderService {

    public void isOk(Long uid, OrderDTO orderDTO);

}

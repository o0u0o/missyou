package com.o0u0o.missyou.service;

import com.o0u0o.missyou.bo.OrderMessageBO;

/**
 * 订单取消业务接口
 * @author o0u0o
 * @date 2021/5/20 7:49 下午
 */
public interface OrderCancelService {

    /**
     * 取消订单
     */
    public void cancel(OrderMessageBO messageBO);
}

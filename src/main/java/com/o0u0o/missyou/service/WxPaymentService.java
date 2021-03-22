package com.o0u0o.missyou.service;

import java.util.Map;

/**
 * @ClassName WxPaymentService
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/19 5:27 下午
 * @Descripton: 微信支付业务接口 以后可拆分为独立的应用
 * @Version: v0.0.1
 **/
public interface WxPaymentService {

    /**
     * 预订单（微信统一订单）业务
     * @param oid 订单号
     * @return
     */
    public Map<String, String> preOrder(Long oid);

}

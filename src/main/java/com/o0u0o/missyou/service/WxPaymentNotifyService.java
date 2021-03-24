package com.o0u0o.missyou.service;

/**
 * 微信在支付通知的业务接口
 * @author o0u0o
 * @date 2021/3/24 10:24 上午
 */
public interface WxPaymentNotifyService {

    public void processPayNotify(String data);
}

package com.github.wxpay.sdk;

import java.io.InputStream;

/**
 * 微信支付配置类
 * @author o0u0o
 * @date 2021/3/22 11:15 上午
 */
public class O0u0oWxPayConfig extends WXPayConfig {

    /**  小程序APPID */
    @Override
    String getAppID() {
        return null;
    }

    /** 商户号 */
    @Override
    String getMchID() {
        return null;
    }

    /** 商户号秘钥 */
    @Override
    String getKey() {
        return null;
    }

    /** 商户证书内容 */
    @Override
    InputStream getCertStream() {
        return null;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return null;
    }


    /**
     * HTTP(S) 连接超时时间，单位毫秒
     *
     * @return
     */
    @Override
    public int getHttpConnectTimeoutMs() {
        return 6*1000;
    }

    /**
     * HTTP(S) 读数据超时时间，单位毫秒
     *
     * @return
     */
    @Override
    public int getHttpReadTimeoutMs() {
        return 8*1000;
    }
}

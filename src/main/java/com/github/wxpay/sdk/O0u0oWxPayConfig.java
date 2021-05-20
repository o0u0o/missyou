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
    public String getAppID() {
        return "wxd898fcb01713c658";
    }

    /** 商户号 */
    @Override
    public String getMchID() {
        return "1483469312";
    }

    /** 商户号秘钥 */
    @Override
    public String getKey() {
        return "098F6BCD4621D373CADE4E832627B4F6";
    }

    /** 商户证书内容 */
    @Override
    public InputStream getCertStream() {
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

    @Override
    public IWXPayDomain getWXPayDomain(){
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }
}

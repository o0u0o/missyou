package com.o0u0o.missyou.service;

/**
 * @ClassName WxAuthenticationService
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/9 4:38 下午
 * @Descripton: 微信登录验证业务接口
 * @Version: v0.0.1
 **/
public interface WxAuthenticationService {

    /**
     * 微信code码登录
     * @param code
     * @return
     */
    public String code2Session(String code);
}

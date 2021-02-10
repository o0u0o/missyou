package com.o0u0o.missyou.service;


import com.o0u0o.missyou.dto.TokenGetDTO;

/**
 * @ClassName AuthenticationService
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/9 4:32 下午
 * @Descripton: 登录验证业务接口
 * @Version: v0.0.1
 **/
public interface AuthenticationService {

    /**
     * 邮箱登录
     * @param tokenGetDTO
     */
    public void getTokenByEmail(TokenGetDTO tokenGetDTO);

    /**
     * 通过微信进行登录
     */
    public void validateByWx(TokenGetDTO tokenGetDTO);

    /**
     * 注册
     */
    public void register();
}

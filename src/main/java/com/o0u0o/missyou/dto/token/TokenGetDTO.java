package com.o0u0o.missyou.dto.token;

import com.o0u0o.missyou.core.enumeration.LoginType;
import com.o0u0o.missyou.dto.validators.TokenPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName TokenGetDTO
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/9 3:30 下午
 * @Descripton: 令牌DTO
 *  备注：account和password在不同场景的含义不一样
 *      1、如邮箱登录：account为email password为用户输入的密码
 *      2、手机号登录：account为手机号 password为短信验证码
 *      3、微信登录：account为微信code码
 * @Version: v0.0.1
 **/
@Getter
@Setter
public class TokenGetDTO {

    @NotBlank(message = "account不允许为空")
    private String account;

    @TokenPassword(max = 30, message = "{token.password}")
    private String password;

    /** 登录类型 */
    private LoginType type;
}

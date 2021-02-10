package com.o0u0o.missyou.core.enumeration;

import lombok.Getter;

/**
 * @ClassName TokenController
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/9 3:58 下午
 * @Descripton: 登录枚举
 * @Version: v0.0.1
 **/
@Getter
public enum LoginType {
    USER_WX(0, "微信登录"),
    USER_EMAIL(1, "邮箱登录");

    private Integer value;
    private String description;

    private LoginType(Integer value, String description){
        this.value = value;
        this.description = description;
    }
}

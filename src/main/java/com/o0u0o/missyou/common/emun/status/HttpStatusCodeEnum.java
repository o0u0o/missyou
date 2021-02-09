package com.o0u0o.missyou.common.emun.status;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName HttpStatusCodeEnum
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/8 上午12:24
 * @Descripton: 描述信息
 * @Version: v0.0.1
 **/
@Getter
public enum HttpStatusCodeEnum {

    NOT_FOUNT(404, "找不到资源"),

    FORBIDDEN(403, "禁止访问")

    ;
    private Integer code;
    private String desc;

    HttpStatusCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

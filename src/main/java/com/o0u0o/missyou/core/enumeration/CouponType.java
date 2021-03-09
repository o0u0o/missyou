package com.o0u0o.missyou.core.enumeration;


import lombok.Getter;

import java.util.stream.Stream;

/**
 * @ClassName CouponType
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/9 5:11 下午
 * @Descripton: 优惠券枚举类型
 * @Version: v0.0.1
 **/
@Getter
public enum CouponType {

    FULL_MINUS(1, "满减券"),

    FULL_OFF(2, "满减折扣券"),

    NO_THRESHOLD_MINUS(3, "无门槛减除券");

    private int value;

    CouponType(int value, String description){
        this.value = value;
    }

    public int value(){
        return this.value;
    }

    public static CouponType toType(int value){
        return Stream.of(CouponType.values())
                .filter(c -> c.value == value)
                .findAny()
                .orElse(null);

    }
}

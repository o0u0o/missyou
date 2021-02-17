package com.o0u0o.missyou.core.enumeration;

/**
 * @ClassName CouponStatus
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/17 1:33 下午
 * @Descripton: 优惠券状态
 * @Version: v0.0.1
 **/
public enum CouponStatus {
    AVAILABLE(1, "可以使用,未过期"),
    USED(2, "已使用"),
    EXPIRED(3, "未使用,已过期");

    private Integer value;
    private String description;

    public Integer getValue() {
        return value;
    }

    private CouponStatus(Integer value, String description){
        this.value = value;
        this.description = description;
    }
}

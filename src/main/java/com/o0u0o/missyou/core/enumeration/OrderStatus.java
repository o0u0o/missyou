package com.o0u0o.missyou.core.enumeration;

import java.util.stream.Stream;

/**
 * 订单状态枚举类
 * @author o0u0o
 * @date 2020/12/16 3:12 下午
 */
public enum OrderStatus {
    All(0, "全部"),

    UNPAID(1, "待支付"),

    PAID(2, "已支付"),

    DELIVERED(3, "已发货"),

    FINISHED(4, "已完成"),

    CANCELED(5, "已取消"),

    // 防止超卖，使用预扣库存避免 缺点：用户下单时扣除，但未支付，过期时间需要还原库存
    // 预扣除库存不存在以下这两种情况
    PAID_BUT_OUT_OF(21, "已支付，但无货或库存不足"),
    DEAL_OUT_OF(22, "已处理缺货但支付的情况");

    private int value;

    OrderStatus(int value, String text) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static OrderStatus toType(int value) {
        return Stream.of(OrderStatus.values())
                .filter(c -> c.value == value)
                .findAny()
                .orElse(null);
    }

}

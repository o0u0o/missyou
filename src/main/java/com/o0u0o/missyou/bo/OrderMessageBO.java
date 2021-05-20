package com.o0u0o.missyou.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单的消息业务对象
 * @author o0u0o
 * @date 2021/5/20 7:54 下午
 */
@Getter
@Setter
public class OrderMessageBO {

    private Long orderId;

    private Long couponId;

    private Long userId;

    private String message;

    public OrderMessageBO(String message){
        this.message = message;
        this.parseId(message);
    }

    //解析（转换）为orderId、couponId、userId
    private void parseId(String message){
        String[] temp = message.split(",");
        this.userId = Long.valueOf(temp[0]);
        this.orderId = Long.valueOf(temp[1]);
        this.couponId = Long.valueOf(temp[2]);
    }
}

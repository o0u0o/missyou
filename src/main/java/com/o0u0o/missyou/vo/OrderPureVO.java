package com.o0u0o.missyou.vo;

import com.o0u0o.missyou.model.Order;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * 简易的订单视图对象
 *
 * @author o0u0o
 * @date 2020/12/16 3:01 下午
 */
@Getter
@Setter
public class OrderPureVO extends Order {

    /** 过期秒数 */
    private Long period;

    /** 创建时间 */
    private Date createTime;

    public OrderPureVO(Order order, Long period) {
        BeanUtils.copyProperties(order, this);
        this.period = period;
    }
}

package com.o0u0o.missyou.vo;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单ID视图对象
 * @ClassName OrderSimplifyVO
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/18 5:26 下午
 * @Descripton: 简单订单视图对象
 * @Version: v0.0.1
 **/
@Getter
@Setter
public class OrderSimplifyVO {
    private Long id;
    private String orderNo;
    private BigDecimal totalPrice;
    private Long totalCount;
    private String snapImg;
    private String snapTitle;
    private BigDecimal finalTotalPrice;
    private Integer status;
    /** 订单过期时间 */
    private Date expiredTime;
    /** 下单时间 */
    private Date placedTime;
    /** 过期时常 便于前端倒计时 */
    private Long period;

}

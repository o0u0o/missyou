package com.o0u0o.missyou.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName SchoolDTO
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/5 11:33 上午
 * @Descripton: 订单DTO
 * 前端也需要计算总价格，前端需要给用户展示折扣后的最终价格。
 * 1、后端计算不适用前端结算的价格信息
 * 2、前后端对比价格是否一样，如果不是需要提醒给用户
 * @Version: v0.0.1
 **/
@Getter
@Setter
public class OrderDTO {

    /** 原总价 前端提交价格不可信  */
    @DecimalMin(value = "0.00", message = "不再合法范围内")
    @DecimalMax(value = "99999999.99", message = "不再合法范围内")
    private BigDecimal totalPrice;

    /** 实际支付总价 */
    private BigDecimal finalTotalPrice;

    /** 优惠券ID */
    private Long couponId;

    /** sku信息 */
    private List<SkuInfoDTO> skuInfoList;

    /** 用户收货地址 */
    private OrderAddressDTO address;
}

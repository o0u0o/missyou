package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName ActivityCoupon
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/11 下午6:06
 * @Descripton: 优惠券活动
 * @Version: v0.0.1
 **/
@Entity
@Getter
@Setter
public class ActivityCoupon {

    /** 主键ID */
    @Id
    private Integer id;

    /** 优惠券ID */
    private Integer couponId;

    /** 活动ID */
    private Integer activityId;
}


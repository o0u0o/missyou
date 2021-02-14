package com.o0u0o.missyou.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ClassName UserCoupon
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/11 下午4:04
 * @Descripton: 用户优惠券
 * @Version: v0.0.1
 **/
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 优惠券ID */
    private Integer couponId;

    /** 订单ID */
    private Integer orderId;

    /**
     * 1:未使用，2：已使用， 3：已过期
     */
    private Byte status;

    /**  创建时间 */
    private Date createTime;

}


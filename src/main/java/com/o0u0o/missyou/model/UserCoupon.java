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

    private Integer userId;

    private Integer couponId;

    /**
     * 1:未使用，2：已使用， 3：已过期
     */
    private Byte status;

    private Date createTime;

    private Integer orderId;

    private Date updateTime;
}


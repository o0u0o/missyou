package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Coupon
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/11 下午4:07
 * @Descripton: 优惠券实体
 * @Version: v0.0.1
 **/
@Entity
@Getter
@Setter
@Where(clause = "delete_time is null")
public class Coupon extends BaseEntity {

    /** 优惠券主键ID */
    @Id
    private Integer id;

    /** 活动ID */
    private Integer activityId;

    /** 优惠券标题 */
    private String title;

    /** 优惠券可使用的开始时间 */
    private Date startTime;

    /** 优惠券可使用的结束时间 */
    private Date endTime;

    /** 描述 */
    private String description;

    /** 满减（多用于满减券） 满多少减 */
    private BigDecimal fullMoney;

    /** 减多少 */
    private BigDecimal minus;

    /** 折扣（多用于折扣券） */
    private BigDecimal rate;

    /** 对优惠券的说明 */
    private String remark;

    /** 当前优惠券是否是全场券 */
    private Boolean wholeStore;

    /** 优惠券类型：1. 满减券 2.折扣券 3.无门槛券 4.满金额折扣券 */
    private Short type;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "couponList")
    private List<Category> categoryList;
}


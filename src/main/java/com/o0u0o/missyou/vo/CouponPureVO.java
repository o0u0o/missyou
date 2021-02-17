package com.o0u0o.missyou.vo;

import com.o0u0o.missyou.model.Coupon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CouponPureVO
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2021/2/15 6:43 下午
 * @Descripton: 优惠券简单VO
 * @Version: v0.0.1
 **/
@Getter
@Setter
@NoArgsConstructor
public class CouponPureVO {
    /** 优惠券主键ID */
    private Long id;

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

    /** 优惠券类型：1. 满减券 2.折扣券 3.无门槛券 4.满金额折扣券 */
    private Integer type;

    /** 对优惠券的说明 */
    private String remark;

    /** 当前优惠券是否是全场券 */
    private Boolean wholeStore;

    public CouponPureVO(Object[] objects){
        Coupon coupon = (Coupon) objects[0];
        BeanUtils.copyProperties(coupon, this);
    }

    public CouponPureVO(Coupon coupon){
        BeanUtils.copyProperties(coupon, this);
    }

    public static List<CouponPureVO> getList(List<Coupon>  couponList){
        List<CouponPureVO> vos = new ArrayList<>();
        return couponList.stream()
                .map(CouponPureVO::new)
                .collect(Collectors.toList());
    }
}

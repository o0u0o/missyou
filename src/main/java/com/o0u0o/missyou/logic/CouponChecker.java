package com.o0u0o.missyou.logic;

import com.o0u0o.missyou.common.utils.CommonUtil;
import com.o0u0o.missyou.core.exception.http.ForbiddenException;
import com.o0u0o.missyou.core.exception.http.ParameterException;
import com.o0u0o.missyou.model.Coupon;
import com.o0u0o.missyou.model.UserCoupon;

import java.util.Date;

/**
 * @ClassName CouponChecker
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Descripton: 优惠券检查逻辑
 * @Date 2021/3/9 4:11 下午
 * @Version: v0.0.1
 **/
public class CouponChecker {

    private Coupon coupon;

    private UserCoupon userCoupon;

    /**
     * 该构造函数传递Model
     * @param coupon 优惠券实体
     * @param userCoupon
     */
    public CouponChecker(Coupon coupon, UserCoupon userCoupon){
        this.coupon = coupon;
        this.userCoupon = userCoupon;
    }

    public void isOk(){
        //1、检查优惠券是否过期
        Date now = new Date();
        //是否在一个时间线 不再统一个时间线，视为优惠券已经过期
        Boolean isInTimeline = CommonUtil.isInTimeLine(now, this.coupon.getStartTime(), this.coupon.getEndTime());
        if (!isInTimeline){
            throw new ForbiddenException(40007);
        }
    }

    /**
     * 检查最终总价格是否正确
     */
    public void finalTotalPriceIsOk(){

    }

    /**
     * 核对当前优惠券能否被使用
     */
    public void canBeUsed(){

    }

}

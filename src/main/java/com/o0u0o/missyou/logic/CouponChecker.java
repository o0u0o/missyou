package com.o0u0o.missyou.logic;

import com.o0u0o.missyou.common.utils.CommonUtil;
import com.o0u0o.missyou.core.enumeration.CouponType;
import com.o0u0o.missyou.core.exception.http.ForbiddenException;
import com.o0u0o.missyou.model.Coupon;
import com.o0u0o.missyou.model.UserCoupon;

import java.math.BigDecimal;
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
     * 价格参数：
     * （1）前端计算的订单价格 和 前端计算的订单折扣价（实际支付价格）
     * （2）服务端计算订单的价格 和 服务端计算的订单折扣价（实际支付价格）
     *
     * 问题：
     *  1、浮点数运算精度问题
     *  java中避免计算浮点数导致不精确问题，使用BigDecimal来计算
     *  2、取舍问题
     *      （1）四舍五入（相对折中的方案）
     *      （2）社区厘之后的数
     *      （3）四舍六入 可以较比精确计算 难点在于对5的取舍 可以使用银行家算法来解决
     *      银行家算法（IEEE标准）：
     *      四舍六入五考虑，五后非空就进一，五后为空看奇偶，五前为偶应舍弃，五前为奇要进一
     *  如果不让平台亏钱，都向上取整进一
     *
     *
     *
     *
     * @param orderFinalTotalPrice 前端最终总价
     * @param serverTotalPrice 后端总价（没计算折扣后的）
     */
    public void finalTotalPriceIsOk(BigDecimal orderFinalTotalPrice,
                                    BigDecimal serverTotalPrice){
        //实际总价格 = 总价格 - 使用优惠券后的折扣价
        BigDecimal serverFinalTotalPrice;
        switch (CouponType.toType(this.coupon.getType())){
            //满减券(满多少减多少)计算实际总价格
            case FULL_MINUS:
                serverFinalTotalPrice = serverTotalPrice.subtract(this.coupon.getMinus());
                int compare = serverFinalTotalPrice.compareTo(orderFinalTotalPrice);
                //如果价格不一致
                if (compare != 0){
                    throw new ForbiddenException(50008);
                }
                break;
            //满减折扣券
            case FULL_OFF:


        }
    }

    /**
     * 核对当前优惠券能否被使用
     */
    public void canBeUsed(){

    }

}

package com.o0u0o.missyou.service;

import com.o0u0o.missyou.model.Coupon;

import java.util.List;

/**
 * @ClassName CouponService
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/17 8:42 上午
 * @Descripton: 优惠券业务接口
 * @Version: v0.0.1
 **/
public interface CouponService {

    /**
     * 根据分类获取优惠券
     * @param cid
     * @return
     */
    List<Coupon> getByCategory(Long cid);

    /**
     * 查询全场券
     * @return
     */
    List<Coupon> getWholeStoreCoupons();

    /**
     * 领取一张优惠券
     * @param uid 用户id
     * @param couponId 优惠券id
     */
    void collectOneCoupon(Long uid, Long couponId);
}

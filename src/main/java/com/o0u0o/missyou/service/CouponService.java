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

    List<Coupon> getByCategory(Long cid);
}

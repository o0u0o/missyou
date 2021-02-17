package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.model.Coupon;
import com.o0u0o.missyou.repository.ActivityRepository;
import com.o0u0o.missyou.repository.CouponRepository;
import com.o0u0o.missyou.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CouponServiceImpl
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/17 8:44 上午
 * @Descripton: 优惠券业务实现类
 * @Version: v0.0.1
 **/
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ActivityRepository activityRepository;

    /**
     * 根据分类获取优惠券
     * 活动未过期的
     * @param cid
     * @return
     */
    @Override
    public List<Coupon> getByCategory(Long cid) {
        Date now = new Date();
        return couponRepository.findByCategory(cid, now);
    }

    /**
     * 获取全场券
     * @return
     */
    @Override
    public List<Coupon> getWholeStoreCoupons() {
        Date now = new Date();
        return couponRepository.findByWholeStore(true, now);
    }
}

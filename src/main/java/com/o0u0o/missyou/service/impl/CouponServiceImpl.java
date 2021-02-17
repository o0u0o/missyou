package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.common.utils.CommonUtil;
import com.o0u0o.missyou.core.enumeration.CouponStatus;
import com.o0u0o.missyou.core.http.NotFoundException;
import com.o0u0o.missyou.core.http.ParameterException;
import com.o0u0o.missyou.model.Activity;
import com.o0u0o.missyou.model.Coupon;
import com.o0u0o.missyou.model.UserCoupon;
import com.o0u0o.missyou.repository.ActivityRepository;
import com.o0u0o.missyou.repository.CouponRepository;
import com.o0u0o.missyou.repository.UserCouponRepository;
import com.o0u0o.missyou.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private UserCouponRepository userCouponRepository;

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

    /**
     * 领取优惠券
     * @param uid 用户id
     * @param cid 被领取优惠券id
     */
    @Override
    public void collectOneCoupon(Long uid, Long couponId) {
        //1、优惠券是否存在
        this.couponRepository
                .findById(couponId)
                .orElseThrow(() -> new NotFoundException(40003));

        //2、优惠券是否过期（活动activity是否过期）
        Activity activity = this.activityRepository
                .findByCouponListId(couponId)
                .orElseThrow(() -> new NotFoundException(40010));
        Date now = new Date();
        Boolean isIn = CommonUtil.isInTimeLine(now, activity.getStartTime(), activity.getEndTime());
        if (!isIn){
            throw new ParameterException(40005);
        }

        //3、用户已经领取了优惠券
        this.userCouponRepository
                .findFirstByCouponId(uid, couponId)
                .orElseThrow(() -> new ParameterException(40006));

        //4、构建UserCoupon并保存
        UserCoupon userCouponNew = UserCoupon.builder()
                .userId(uid)
                .couponId(couponId)
                .status(CouponStatus.AVAILABLE.getValue())
                .createTime(now)
                .build();
        userCouponRepository.save(userCouponNew);
    }
}

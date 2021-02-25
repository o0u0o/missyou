package com.o0u0o.missyou.api.v1;


import com.o0u0o.missyou.core.LocalUser;
import com.o0u0o.missyou.core.UnifyResponse;
import com.o0u0o.missyou.core.enumeration.CouponStatus;
import com.o0u0o.missyou.core.exception.http.ParameterException;
import com.o0u0o.missyou.core.interceptors.annotation.ScopeLevel;
import com.o0u0o.missyou.model.Coupon;
import com.o0u0o.missyou.model.User;
import com.o0u0o.missyou.service.CouponService;
import com.o0u0o.missyou.vo.CouponCategoryVO;
import com.o0u0o.missyou.vo.CouponPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.o0u0o.missyou.core.exception.success.CreateSuccess;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CouponController
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2021/2/17 8:34 上午
 * @Descripton: 优惠券控制器
 * @Version: v0.0.1
 **/
@RequestMapping("coupon")
@RestController
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/by/category/{cid}")
    public List<CouponPureVO> getCouponListByCategory(@PathVariable Long cid){
        List<Coupon> coupons = couponService.getByCategory(cid);
        if (coupons.isEmpty()){
            return Collections.emptyList();
        }
        List<CouponPureVO> vos = CouponPureVO.getList(coupons);
        return vos;
    }

    /**
     * 查询全场券
     * @return
     */
    @GetMapping("/whole_store")
    public List<CouponPureVO> getWholeStoreCouponList(){
        List<Coupon> coupons = this.couponService.getWholeStoreCoupons();
        if (coupons.isEmpty()){
            return Collections.emptyList();
        }
        return CouponPureVO.getList(coupons);
    }


    /**
     * ScopeLevel 必须登录用户才可领取
     * 注意：用户id不能显式传输到服务端 通过jwt令牌中获取用户id
     * 原因：
     *  1、可能出现超权问题 防止客户端篡改用户的id进行操作
     * 用户领取优惠券
     * @param id 优惠券id
     */
    @ScopeLevel()
    @PostMapping("/collect/{id}")
    public void collectCoupon(@PathVariable Long id){
        Long uid = LocalUser.getUser().getId();
        couponService.collectOneCoupon(uid, id);
        UnifyResponse.createSuccess(0);
    }

    /**
     * 根据状态来获得我的优惠券
     * 难点：
     * 1、需要涉及到优惠券规划：
     * 场景：用户使用优惠券下单后用户取消支付或者未支付，此时该使用的优惠券需要归还给用户
     * @param status 状态
     *               1-未使用 2-已使用 3-已过期
     *
     *       触发机制：
     *               1、主动触发
     *                  定时器
     *               2、被动触发
     *                  redis / rocketMQ / rabbitMq
     * @return
     */
    @ScopeLevel
    @GetMapping("/myself/by/status/{status}")
    public List<CouponPureVO> getMyCouponByStatus(@PathVariable Integer status){
        Long uid = LocalUser.getUser().getId();
        List<Coupon> couponList;

        //要考虑到延时订单支付使用了优惠券，未支付时需要退还
        switch (CouponStatus.toType(status)){
            case AVAILABLE:
                couponList = couponService.getMyAvailableCoupons(uid);
                break;
            case USED:
                couponList = couponService.getMyUsedCoupons(uid);
                break;
            case EXPIRED:
                couponList = couponService.getMyExpiredCoupons(uid);
                break;
            default:
                throw new ParameterException(40001);
        }
        return CouponPureVO.getList(couponList);
    }

    /**
     * 通过分类查询我的可用的优惠券
     * @return
     */
    @ScopeLevel()
    @GetMapping("/myself/available/with_category")
    public List<CouponCategoryVO> getUserCouponWithCategory(){
        User user = LocalUser.getUser();
        List<Coupon> coupons = couponService.getMyAvailableCoupons(user.getId());
        if (coupons.isEmpty()){
            return Collections.emptyList();
        }

        return coupons.stream().map(coupon -> {
            CouponCategoryVO vo = new CouponCategoryVO(coupon);
            return vo;
        }).collect(Collectors.toList());
    }

}

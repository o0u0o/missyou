package com.o0u0o.missyou.api.v1;


import com.o0u0o.missyou.core.LocalUser;
import com.o0u0o.missyou.core.interceptors.annotation.ScopeLevel;
import com.o0u0o.missyou.model.Coupon;
import com.o0u0o.missyou.model.User;
import com.o0u0o.missyou.service.CouponService;
import com.o0u0o.missyou.vo.CouponPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

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
        this.couponService.collectOneCoupon(uid, id);
    }
}

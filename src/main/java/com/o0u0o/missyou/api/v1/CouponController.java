package com.o0u0o.missyou.api.v1;


import com.o0u0o.missyou.model.Coupon;
import com.o0u0o.missyou.service.CouponService;
import com.o0u0o.missyou.vo.CouponPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package com.o0u0o.missyou.vo;

import com.o0u0o.missyou.model.Coupon;
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
public class CouponPureVO {
    private Long id;

    private String title;
    private Date startTime;
    private Date endTime;
    private String description;
    private BigDecimal fullMoney;
    private BigDecimal minus;
    private BigDecimal rate;
    private Integer type;
    private String remark;
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

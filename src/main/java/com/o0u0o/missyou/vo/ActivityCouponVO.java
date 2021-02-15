package com.o0u0o.missyou.vo;

import com.o0u0o.missyou.model.Activity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @ClassName ActivityCouponVO
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/14 11:12 下午
 * @Descripton: 活动(优惠券)视图对象
 * @Version: v0.0.1
 **/
@Getter
@Setter
public class ActivityCouponVO extends ActivityPureVO {

    private List<CouponPureVO> coupons;

    //再次进行属性的精简
    public ActivityCouponVO(Activity activity){
        super(activity);
        coupons = activity.getCouponList()
                .stream().map(CouponPureVO::new)
                .collect(Collectors.toList());
    }
}

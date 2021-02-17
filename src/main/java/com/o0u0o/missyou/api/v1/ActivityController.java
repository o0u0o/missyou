package com.o0u0o.missyou.api.v1;

import com.o0u0o.missyou.core.exception.http.NotFoundException;
import com.o0u0o.missyou.model.Activity;
import com.o0u0o.missyou.service.ActivityService;
import com.o0u0o.missyou.vo.ActivityCouponVO;
import com.o0u0o.missyou.vo.ActivityPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ActivityController
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/14 11:07 下午
 * @Descripton: 活动接口
 * @Version: v0.0.1
 **/
@RequestMapping("activity")
@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 首页活动
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public ActivityPureVO getHomeActivity(@PathVariable String name){
        Activity activity = activityService.getByName(name);
        if (activity == null){
            throw new NotFoundException(40001);
        }
        //精简数据
        ActivityPureVO vo = new ActivityPureVO(activity);
        return vo;
    }

    /**
     * 根据活动名查询优惠券
     * @param name
     * @return
     */
    @GetMapping("/name/{name}/with_coupon")
    public ActivityCouponVO getActivityWithCoupons(@PathVariable String name){
        Activity activity = activityService.getByName(name);
        if (activity == null){
            throw new NotFoundException(40001);
        }
        //属性精简
        return new ActivityCouponVO(activity);
    }


}

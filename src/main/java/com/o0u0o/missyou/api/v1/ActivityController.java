package com.o0u0o.missyou.api.v1;

import com.o0u0o.missyou.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
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


}

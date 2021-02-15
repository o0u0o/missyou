package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.model.Activity;
import com.o0u0o.missyou.repository.ActivityRepository;
import com.o0u0o.missyou.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ActivityServiceImpl
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/14 11:11 下午
 * @Descripton: 活动业务接口实现
 * @Version: v0.0.1
 **/
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity getByName(String name) {
        return activityRepository.findByName(name);
    }
}

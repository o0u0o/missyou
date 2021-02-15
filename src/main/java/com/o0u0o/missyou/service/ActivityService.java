package com.o0u0o.missyou.service;


import com.o0u0o.missyou.model.Activity;

/**
 * @ClassName ActivityService
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/14 11:10 下午
 * @Descripton: 活动业务接口
 * @Version: v0.0.1
 **/
public interface ActivityService {

    Activity getByName(String name);
}

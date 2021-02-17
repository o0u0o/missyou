package com.o0u0o.missyou.service;

import com.o0u0o.missyou.model.User;

/**
 * @ClassName UserService
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/17 11:42 上午
 * @Descripton: 用户业务接口
 * @Version: v0.0.1
 **/
public interface UserService {

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    public User getUserById(Long id);
}

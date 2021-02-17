package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.model.User;
import com.o0u0o.missyou.repository.UserRepository;
import com.o0u0o.missyou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/17 11:44 上午
 * @Descripton: 用户业务实现类
 * @Version: v0.0.1
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findFirstById(id);
    }
}

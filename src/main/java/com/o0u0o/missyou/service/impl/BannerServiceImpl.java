package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.model.Banner;
import com.o0u0o.missyou.repository.BannerRepository;
import com.o0u0o.missyou.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BannerServiceImpl
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/13 下午11:23
 * @Descripton: 描述信息
 * @Version: v0.0.1
 **/
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerRepository repository;

    @Override
    public Banner getByName(String name) {
        return repository.findOneByName(name);
    }
}

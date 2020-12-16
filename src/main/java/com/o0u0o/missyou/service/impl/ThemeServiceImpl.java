package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.model.Theme;
import com.o0u0o.missyou.repository.ThemeRepository;
import com.o0u0o.missyou.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ThemeServiceImpl
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/27 下午11:42
 * @Descripton: 主题业务
 * @Version: v0.0.1
 **/
@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Override
    public List<Theme> findByNames(List<String> names) {
        return themeRepository.findByNames(names);
    }

    public Optional<Theme> findByName(String name){
        return themeRepository.findByName(name);
    }
}

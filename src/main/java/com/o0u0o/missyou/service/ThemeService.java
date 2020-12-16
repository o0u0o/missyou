package com.o0u0o.missyou.service;

import com.o0u0o.missyou.model.Theme;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ThemeService
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/27 下午11:41
 * @Descripton: 主题业务
 * @Version: v0.0.1
 **/
public interface ThemeService {

    public List<Theme> findByNames(List<String> names);

    public Optional<Theme> findByName(String themeName);
}

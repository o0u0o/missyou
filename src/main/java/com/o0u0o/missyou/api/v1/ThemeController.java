package com.o0u0o.missyou.api.v1;

import com.o0u0o.missyou.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ThemeController
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/27 下午11:39
 * @Descripton: 主题接口
 * @Version: v0.0.1
 **/
@RestController
@RequestMapping("/theme")
public class ThemeController {

    @Autowired
    ThemeService themeService;



}

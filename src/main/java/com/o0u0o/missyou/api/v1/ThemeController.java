package com.o0u0o.missyou.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.o0u0o.missyou.core.http.NotFoundException;
import com.o0u0o.missyou.model.Theme;
import com.o0u0o.missyou.service.ThemeService;
import com.o0u0o.missyou.vo.ThemePureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/by/names")
    public List<ThemePureVO> getThemeGroupByNames(@RequestParam(name = "names") String names ){
        List<String> nameList = Arrays.asList(names.split(","));
        List<Theme> themes = themeService.findByNames(nameList);
        List<ThemePureVO> list = new ArrayList<>();
        themes.forEach(theme -> {
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            ThemePureVO vo = mapper.map(theme, ThemePureVO.class);
            list.add(vo);
        });
        return list;
    }

    @GetMapping("/name/{name}/with_spu")
    public Theme getThemeByNameWithSpu(@PathVariable(name = "name") String themeName){
        Optional<Theme> optionalTheme = this.themeService.findByName(themeName);
        //Optional简化代码: 1、Optional和if else 没多大区别 2、Optional提高的判空的标准(强制我们来判断空值的情况)
        return optionalTheme.orElseThrow(() -> new NotFoundException(30003));
    }

}

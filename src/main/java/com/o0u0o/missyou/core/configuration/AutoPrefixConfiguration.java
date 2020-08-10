package com.o0u0o.missyou.core.configuration;

import com.o0u0o.missyou.core.hack.AutoPrefixUrlMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @ClassName AutoPrefixConfiguration
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/9 下午1:21
 * @Descripton: AutoPrefix 的配置类
 * 1、需继承WebMvcRegistrations
 * 2、实现 WebMvcRegistrations接口的 RequestMappingHandlerMapping 方法
 * 3、使用 @Component 注解加入 IOC容器
 *
 * 发现机制两种模式:
 *  1、通过特定注解比如(打上@ControllerAdvice ioc容器就知道是异常处理)
 *  2、通过实现特定接口,
 * @Version: v0.0.1
 **/
@Component
public class AutoPrefixConfiguration implements WebMvcRegistrations {

    /**
     * 实例化AutoPrefixUrlMapping 并返回
     * @return
     */
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new AutoPrefixUrlMapping();
    }

}

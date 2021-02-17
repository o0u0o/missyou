package com.o0u0o.missyou.core.configuration;

import com.o0u0o.missyou.core.interceptors.PermissionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName InterceptorConfiguration
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/10 5:18 下午
 * @Descripton: 拦截器注册类
 * @Version: v0.0.1
 **/
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {


    @Bean
    public HandlerInterceptor getPermissionInterceptor(){
        return new PermissionInterceptor();
    }

    /**
     * 注册Interceptor
     * 可以注册多个Interceptor
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getPermissionInterceptor());
    }
}

package com.o0u0o.missyou.core.interceptors;


import com.auth0.jwt.interfaces.Claim;
import com.o0u0o.missyou.common.utils.JwtToken;
import com.o0u0o.missyou.core.http.ForbiddenException;
import com.o0u0o.missyou.core.http.UnAuthenticatedException;
import com.o0u0o.missyou.core.interceptors.annotation.ScopeLevel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName PermissionInterceptor
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/10 4:33 下午
 * @Descripton: 许可拦截器
 * @Version: v0.0.1
 **/
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    public PermissionInterceptor() {
        super();
    }

    /**
     * 1、获取请求里的token令牌
     * 2、验证token令牌
     * 3、如果是公开api，无需验证许可也可访问，非公开的要判别scopeLevel
     * 4、scope > level  可以访问
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        Optional<ScopeLevel> scopeLevel = this.getScopeLevel(handler);
        //如果没有获取到scopeLevel 就是个公开的api 不需要对比token
        if (!scopeLevel.isPresent()){
            return true;
        }

        //获取token
        String token = this.getToken(request);

        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaims(token);
        Map<String, Claim> map = optionalMap.orElseThrow(
                ()->new UnAuthenticatedException(10004));


        boolean hasPermission = this.hasPermission(scopeLevel.get(), map);

        return hasPermission;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {

    }

    /**
     * 获取scope等级
     * @param handler
     * @return
     */
    private Optional<ScopeLevel> getScopeLevel(Object handler){
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            //获取注解
            ScopeLevel scopeLevel = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            return Optional.of(scopeLevel);
        }
        return Optional.empty();
    }

    /**
     * 获取token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request){
        /** 获取token */
        String bearerToken = request.getHeader("Authorization");
        //判断是否携带token 没携带令牌抛出异常
        if (StringUtils.isEmpty(bearerToken)){
            throw new UnAuthenticatedException(10004);
        }

        //如果有的客户端使用的是Bearer的标准
        if (!bearerToken.startsWith("Bearer")){
            throw new UnAuthenticatedException(10004);
        }
        //提取jwt令牌
        String token = bearerToken.split(" ")[1];
        return token;
    }

    /**
     * 是否有权限访问
     * @param scopeLevel
     * @param map
     * @return
     */
    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> map){
        //获取注解里的权限等级
        Integer level = scopeLevel.value();
        Integer scope = map.get("scope").asInt();
        //不允许访问
        if (level > scope){
            throw new ForbiddenException(10005);
        }
        return true;
    }
}

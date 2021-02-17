package com.o0u0o.missyou.core;

import com.o0u0o.missyou.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ThemeServiceImpl
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/17 11:34 上午
 * @Descripton: 本地用户类
 * 问题：
 * 1、静态变量通常只能保存数据，无法保存状态，静态变量是全局共享的，多用户访问时是无法确定
 * 2、解决保存多个用户对象且解决线程安全 使用ThreadLocal
 *
 * ThreadLocal: 记录多线程环境下的数据
 * 1、通过线程来考虑用户访问，每一个请求就是不一样的线程
 * 2、使用threadlocal无需传入key，key由threadlocal进行管理(其实就是线程的key)
 * 3、调用clear方法在拦截器PermissionInterceptor的afterCompletion方法中
 * @Version: v0.0.1
 **/
public class LocalUser {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    /**
     * 设置用户信息和权限等级
     * @param user 用户信息
     * @param scope 权限等级
     */
    public static void set(User user, Integer scope){
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("scope", scope);
        //写入到threadLocal
        LocalUser.threadLocal.set(map);
    }

    /**
     * 获取用户信息
     * @return
     */
    public static User getUser(){
        Map<String, Object> map = LocalUser.threadLocal.get();
        User user = (User)map.get("user");
        return user;
    }

    /**
     * 获取权限等级
     * @return
     */
    public static Integer getScope(){
        Map<String, Object> map = LocalUser.threadLocal.get();
        Integer scope = (Integer)map.get("scope");
        return scope;
    }

    /**
     * 释放线程资源
     */
    public static void clear(){
        LocalUser.threadLocal.remove();
    }
}

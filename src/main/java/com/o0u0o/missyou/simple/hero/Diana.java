package com.o0u0o.missyou.simple.hero;

import com.o0u0o.missyou.simple.ISkill;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @Author aiuiot
 * @Date 2020/2/15 6:49 下午
 * @Descripton: 英雄 Diana
 * 使用 @Component 注解自动加入到Spring容器
 * 注解 @Lazy 延迟实例化
 **/

//@Component
//@Lazy
public class Diana implements ISkill {

    private String skillName = "R技能";

    private String name;

    private Integer age;

    public Diana(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public Diana(){
        System.out.println("Diana 被实例化");
    }

    @Override
    public void q(){
        System.out.println("Diana Q");
    }

    @Override
    public void w(){
        System.out.println("Diana W");
    }

    @Override
    public void e(){
        System.out.println("Diana E");
    }

    @Override
    public void r(){
        System.out.println("Diana R");
    }
}


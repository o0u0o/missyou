package com.o0u0o.missyou.simple;

import com.o0u0o.missyou.simple.condtion.DianaCondation;
import com.o0u0o.missyou.simple.condtion.IreliaCondition;
import com.o0u0o.missyou.simple.hero.Camille;
import com.o0u0o.missyou.simple.hero.Diana;
import com.o0u0o.missyou.simple.hero.Irelia;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author aiuiot
 * @Date 2020/3/15 11:45 上午
 * @Descripton: 英雄配置类
 * 配置类中需要使用 @Configuration 模式直接
 * 作用：解决以前Spring的xml配置
 **/
@Configuration
public class HeroConfiguration {

//    @Bean
//    public ISkill camille(){
//        return new Camille("卡密尔", 18);
//    }

    /**
     * Conditional 自定义一个条件注解
     * 注解@ConditionalOnProperty
     * @return
     */
    //@Bean
    //@Conditional(DianaCondation.class)
    //@ConditionalOnProperty(value = "hero.condition", havingValue = "diana", matchIfMissing = true)
    //@ConditionalOnBean(name = "mysql")
//    @ConditionalOnMissingBean(name = "mysql")
    public ISkill diana(){
        return new Diana("黛安娜", 18);
    }

    @Bean
    //@Conditional(IreliaCondition.class)
    //@ConditionalOnProperty(value = "hero.condition", havingValue = "irelia")
    public ISkill irelia(){
        return new Irelia();
    }


}

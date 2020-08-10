package com.o0u0o.missyou;

import com.o0u0o.missyou.simple.EnableLOLConfiguration;
import com.o0u0o.missyou.simple.HeroConfiguration;
import com.o0u0o.missyou.simple.ISkill;
import com.o0u0o.missyou.simple.LOLConfigurationSeletor;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @ClassName LOLApplication
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/6/30 下午11:48
 * @Descripton: 自己实现的启动类
 * @Version: v0.0.1
 **/
//@ComponentScan
//@Import(HeroConfiguration.class)
//@Import(LOLConfigurationSeletor.class)
@EnableLOLConfiguration
public class LOLApplication {
    public static void main(String[] args) {
        //自己生成一个context
        ConfigurableApplicationContext context = new SpringApplicationBuilder(LOLApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        //测试代码
        ISkill iSkill = (ISkill) context.getBean("irelia");
        iSkill.r();
    }
}

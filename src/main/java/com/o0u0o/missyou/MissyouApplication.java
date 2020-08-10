package com.o0u0o.missyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 教学方案
 * 问题 -> 理论 -> 解决方案
 * 如：（软件工程畏惧）-> OCP开闭原则
 * 精选经典案例
 *
 * 注解 @ComponentScan 包扫描注解
 */
@SpringBootApplication
@ComponentScan("com.o0u0o")
public class MissyouApplication {

    public static void main(String[] args) {
        SpringApplication.run(MissyouApplication.class, args);
    }

}

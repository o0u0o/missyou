package com.o0u0o.missyou.simple.condtion;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * @Author 条件注解类
 * @Date 2020/5/19 11:07 下午
 * @Descripton:
 **/
public class DianaCondation implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String name = conditionContext.getEnvironment().getProperty("hero.condition");

        return "diana".equalsIgnoreCase(name);
    }
}

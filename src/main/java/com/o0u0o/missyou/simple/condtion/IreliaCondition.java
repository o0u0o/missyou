package com.o0u0o.missyou.simple.condtion;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author aiuiot
 * @Date 2020/5/19 11:11 下午
 * @Descripton:
 **/
public class IreliaCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String name = conditionContext.getEnvironment().getProperty("hero.condition");
        return "irelia".equalsIgnoreCase(name);
    }
}

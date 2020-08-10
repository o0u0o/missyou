package com.o0u0o.missyou.simple;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(LOLConfigurationSeletor.class)
public @interface EnableLOLConfiguration {
}

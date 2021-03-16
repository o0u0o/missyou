package com.o0u0o.missyou.simple;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author mac
 * @date 2021/3/10 10:36 上午
 */
@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class Test {
    private String name = "7";
}

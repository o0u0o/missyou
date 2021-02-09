package com.o0u0o.missyou.dto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = TokenPasswordValidator.class)
public @interface TokenPassword {

    /** 消息 */
    String message() default "字段不符合要求";

    int min() default 6;

    int max() default 32;

    //2、需要加入两个模版方法 规范需要加入这两个
    Class<?>[] groups() default {};

    Class<? extends Payload> [] payload() default {};

}

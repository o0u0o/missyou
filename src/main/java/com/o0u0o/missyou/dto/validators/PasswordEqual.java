package com.o0u0o.missyou.dto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordEqual {

    /** 最小 4位 */
    int min() default 4;

    /** 最大16位 */
    int max() default 16;

    /** 消息 */
    String message() default "字段不符合要求";


    //2、需要加入两个模版方法 规范需要加入这两个
    Class<?>[] groups() default {};

    Class<? extends Payload> [] payload() default {};

    //3、关联类，用于写业务逻辑

}

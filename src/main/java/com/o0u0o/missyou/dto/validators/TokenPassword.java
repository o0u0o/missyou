package com.o0u0o.missyou.dto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author o0u0o
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Constraint(validatedBy = TokenPasswordValidator.class)
public @interface TokenPassword {

    String message() default "字段不符合要求";

    int min() default 6;

    int max() default 32;

    Class<?>[] groups() default {};

    Class<? extends Payload> [] payload() default {};

}

package com.o0u0o.missyou.dto.validators;


import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName TokenPasswordValidator
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/9 3:45 下午
 * @Descripton: 令牌密码校验器
 * @Version: v0.0.1
 **/
public class TokenPasswordValidator implements ConstraintValidator<TokenPassword, String> {

    private int min;

    private int max;

    @Override
    public void initialize(TokenPassword constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)){
            //如果是小程序登录方式，可以没有密码
            return true;
        }
        return s.length() >= this.min && s.length() <= this.max;
    }
}

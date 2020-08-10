package com.o0u0o.missyou.validators;

import com.o0u0o.missyou.dto.PersonDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName PasswordValidator
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/13 下午9:36
 * @Descripton: 1、ConstraintValidator<a,b>
 *     第一个参数：注解 PasswordEqual
 *     第二个参数：自定义注解修饰的目标的的类型
 * @Version: v0.0.1
 **/
public class PasswordValidator implements ConstraintValidator<PasswordEqual, PersonDTO> {

    private int min;

    private int max;

    @Override
    public void initialize(PasswordEqual constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(PersonDTO personDTO, ConstraintValidatorContext constraintValidatorContext) {

        if (personDTO.getPassword1().isEmpty() && personDTO.equals("")){
            return false;
        }

        if (personDTO.getPassword2().isEmpty() && personDTO.getPassword2().equals("")){
            return false;
        }

        if (personDTO.getPassword1().length() < min && personDTO.getPassword1().length() > max){
            return false;
        }

        if (personDTO.getPassword2().length() < min && personDTO.getPassword2().length() > max){
            return false;                                                                         
        }                                                                                         
        boolean match = personDTO.getPassword1().equals(personDTO.getPassword2());

        return match;
    }


}

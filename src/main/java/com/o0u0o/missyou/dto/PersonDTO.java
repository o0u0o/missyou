package com.o0u0o.missyou.dto;

import com.o0u0o.missyou.dto.validators.PasswordEqual;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @ClassName PersonDTO
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/9 下午9:58
 * @Descripton: Person 数据传输对象
 * 1、@AllArgsConstructor 所有参数的构造函数 不建议使用全参的构造函数
 * 2、生成无参的构造函数
 * 3、生成不为空的构造函数
 * @Version: v0.0.1
 **/
@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@RequiredArgsConstructor
@Builder
@PasswordEqual(min = 2, max = 19, message = "密码不匹配")
public class PersonDTO {

//    @NonNull
    @Length(min = 2, max = 10, message = "长度应该在2～10之间")
    private String name;

    @Max(value = 200, message = "年龄超过200岁无法使用")
    @Min(value = 0, message = "年龄不能小于0岁")
//    @Range(min = 0, max = 100, message = "年龄应为0～100")
    private Integer age;

    /**
     *  使用 @Valid 让相关联的DTO参与验证
     *
     */
//    @Valid
//    private SchoolDTO schoolDTO;

    private String password1;

    private String password2;



}

package com.o0u0o.missyou.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * @ClassName SchoolDTO
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/13 下午9:13
 * @Descripton: 学校DTO
 * @Version: v0.0.1
 **/
@Getter
@Setter
public class SchoolDTO {

    @Length(min = 2, max = 16)
    private String schoolName;
}

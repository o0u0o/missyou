package com.o0u0o.missyou.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName Spec
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/8/25 下午10:10
 * @Descripton: 规格实体类
 * @Version: v0.0.1
 **/
@Getter
@Setter
public class Spec implements Serializable {
    @JsonProperty("key_id")
    private Long keyId;

    private String key;

    @JsonProperty("value_id")
    private Long valueId;

    private String value;
}

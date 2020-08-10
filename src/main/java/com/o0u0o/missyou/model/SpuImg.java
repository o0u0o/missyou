package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName SpuImg
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/29 下午9:31
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
@Getter
@Setter
@Entity
public class SpuImg extends BaseEntity {

    @Id
    private Long id;

    private String img;

    private Long spuId;
}

package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName BannerItemEntity
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/27 下午10:20
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
@Getter
@Setter
@Entity
public class BannerItem extends BaseEntity {

    @Id
    private Long id;

    private String img;

    private String keyword;

    private short type;

    private Long bannerId;

    private String name;
}

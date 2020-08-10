package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName BannerEntity
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/27 下午10:20
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
@Getter
@Setter
@Entity
public class Banner extends BaseEntity {

    @Id
    private int id;

    private String name;

    private String description;

    private String title;

    private String img;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bannerId")
    private List<BannerItem> items;
}

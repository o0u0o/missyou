package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName SpuEntity
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/28 下午11:11
 * @Descripton: SPU 实体
 * @Version: v0.0.1
 **/
@Getter
@Setter
@Entity
public class Spu extends BaseEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String subtitle;

    private Long categoryId;

    private Integer rootCategoryId;

    private Boolean online;

    private String price;

    private Integer sketchSpecId;

    private Integer defaultSkuId;

    private String img;

    private String discountPrice;

    private String description;

    private String tags;

    private Boolean isTest;

//    private Object spuThemeImg;

    private String forThemeImg;

//    @OneToMany(fetch = FetchType.LAZY)
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "spuId")
//    private List<Sku> skuList;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "spuId")
//    private List<SpuImg> spuImgList;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "spuId")
//    private List<SpuDetailImg> spuDetailImgList;
}

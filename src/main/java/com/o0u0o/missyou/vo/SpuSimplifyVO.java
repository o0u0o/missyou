package com.o0u0o.missyou.vo;


import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName SpuSimplifyVO
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/29 下午10:57
 * @Descripton: SPU简单VO
 * @Version: v0.0.1
 **/
@Getter
@Setter
public class SpuSimplifyVO {

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
}

package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @ClassName Sku
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/29 下午9:35
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
@Getter
@Setter
@Entity
public class Sku extends BaseEntity{

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    /** 折扣价 */
    private BigDecimal discountPrice;

    private Boolean online;

    /** 图片 */
    private String img;

    private String title;

    private Long spuId;


    /** 分类ID */
    private Long categoryId;

    /** 父分类ID */
    private Long rootCategoryId;

    private String specs;

    private String code;

    /** 库存量 */
    private Long stock;
}

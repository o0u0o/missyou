package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

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

    /** 价格 */
    private BigDecimal price;

    /** 折扣价 */
    private BigDecimal discountPrice;

    /** 上下架 */
    private Boolean online;

    /** 图片 */
    private String img;

    private String title;

    private Long spuId;

    /** 分类ID */
    private Long categoryId;

    /** 父分类ID */
    private Long rootCategoryId;

    /** 规格相关参数 */
    private String specs;

    /** SKU的唯一标识 用于简化前端计算 */
    private String code;

    /** 库存量 */
    private Long stock;
}

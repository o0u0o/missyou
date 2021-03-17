package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
@Where(clause = "delete_time is null and online = 1")
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

    /** 标题 */
    private String title;

    private Long spuId;

    /** 分类ID */
    private Long categoryId;

    /** 父分类ID */
    private Long rootCategoryId;

    /** 规格相关参数 */
//    private List<Spec> specs;
    private String specs;

    /** 转换器 */
    @Convert(converter = MapAndJson.class)
    private Map<String, Object> test;

    /** SKU的唯一标识 用于简化前端计算 */
    private String code;

    /** 库存量 */
    private Long stock;

    /** 获取真实价格 */
    public BigDecimal getActualPrice(){
        return discountPrice == null ? this.price : this.discountPrice;
    }

//    public List<Spec> getSpecs(){
//        String specs = this.specs;
//        return null;
//    }
}

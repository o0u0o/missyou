package com.o0u0o.missyou.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.o0u0o.missyou.common.utils.GenericAndJson;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
//    @Convert(converter = MapAndJson.class)
//    private Map<String, Object> test;

    /** SKU的唯一标识 用于简化前端计算 */
    private String code;

    /** 库存量 */
    private Long stock;

    /** 获取真实价格 */
    public BigDecimal getActualPrice(){
        return discountPrice == null ? this.price : this.discountPrice;
    }

    public List<Spec> getSpecs(){
        if (this.specs == null){
            return Collections.emptyList();
        }
        return GenericAndJson.jsonToObject(this.specs, new TypeReference<List<Spec>>() {});
    }

    public void setSpecs(List<Spec> specs){
        if (specs.isEmpty()){
            return;
        }
        this.specs = GenericAndJson.objectToJson(specs);
    }

    @JsonIgnore
    public List<String> getSpecValueList(){
        return this.getSpecs().stream()
                .map(Spec::getValue)
                .collect(Collectors.toList());
    }
}

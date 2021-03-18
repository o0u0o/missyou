package com.o0u0o.missyou.model;

import com.o0u0o.missyou.dto.SkuInfoDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName OrderSku
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Descripton: 订单SKU
 * @Date 2021/3/17 5:11 下午
 * @Version: v0.0.1
 **/
public class OrderSku {
    private Long id;

    /** spuId */
    private Long spuId;

    /** 单价*数量 */
    private BigDecimal finalPrice;
    /** 一个sku的单价 */
    private BigDecimal singlePrice;
    /** 规格值 */
    private List<String> specValues;
    /** 订单里sku购买的数量 */
    private Integer count;
    /** 图片 */
    private String img;
    /** 标题 */
    private String title;

    /**
     * 构造函数
     * @param sku 服务端的sku信息
     * @param skuInfoDTO 前端传入的sku订单信息
     */
    public OrderSku(Sku sku, SkuInfoDTO skuInfoDTO){
        this.id = sku.getId();
        this.spuId = sku.getSpuId();
        this.singlePrice = sku.getActualPrice();
        this.finalPrice = sku.getActualPrice().multiply(new BigDecimal(skuInfoDTO.getCount()));
        this.count = skuInfoDTO.getCount();
        this.img = sku.getImg();
        this.title = sku.getTitle();
        this.specValues = sku.getSpecValueList();
    }
}

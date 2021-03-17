package com.o0u0o.missyou.bo;

import com.o0u0o.missyou.dto.SkuInfoDTO;
import com.o0u0o.missyou.model.Sku;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @ClassName PageCounter
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/16 2:20 下午
 * @Descripton: 订单里的SKU 业务层对象
 * @Version: v0.0.1
 **/
@Getter
@Setter
public class SkuOrderBO {

    /** 真实价格 */
    private BigDecimal actualPrice;

    private Integer count;

    /** 分类id（二级） */
    private Long categoryId;

    public SkuOrderBO(Sku sku, SkuInfoDTO skuInfoDTO) {
        this.actualPrice = sku.getActualPrice();
        this.count = skuInfoDTO.getCount();
        this.categoryId = sku.getCategoryId();
    }

    /**
     * 获取总价
     * @return
     */
    public BigDecimal getTotalPrice(){
        return this.actualPrice.multiply(new BigDecimal(this.count));
    }
}

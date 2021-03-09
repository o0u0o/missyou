package com.o0u0o.missyou.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName SkuInfoDTO
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/5 11:38 上午
 * @Descripton: SKU信息
 * @Version: v0.0.1
 **/
@Getter
@Setter
public class SkuInfoDTO {

    /** sku Id */
    private Long id;

    /** sku的数量 */
    private Integer count;
}

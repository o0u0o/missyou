package com.o0u0o.missyou.service;


import com.o0u0o.missyou.model.Sku;

import java.util.List;

/**
 * @ClassName SkuService
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/9 3:58 下午
 * @Descripton: SKU业务接口
 * @Version: v0.0.1
 **/
public interface SkuService {

    /**
     * 根据sku的id集合查询SKU集合
     * @param ids
     * @return
     */
    public List<Sku> getSkuListByIds(List<Long> ids);
}

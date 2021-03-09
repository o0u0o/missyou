package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.model.Sku;
import com.o0u0o.missyou.repository.SkuRepository;
import com.o0u0o.missyou.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SkuServiceImpl
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/9 4:00 下午
 * @Descripton: SKU业务实现类
 * @Version: v0.0.1
 **/
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuRepository skuRepository;

    /**
     * 根据sku的id集合查询SKU集合
     * @param ids
     * @return
     */
    @Override
    public List<Sku> getSkuListByIds(List<Long> ids) {
        return skuRepository.findAllByIdIn(ids);
    }
}

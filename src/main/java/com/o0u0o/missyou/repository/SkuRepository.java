package com.o0u0o.missyou.repository;


import com.o0u0o.missyou.model.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName SkuRepository
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/9 3:54 下午
 * @Descripton: SPU 数据仓库
 * @Version: v0.0.1
 **/
public interface SkuRepository extends JpaRepository<Sku, Long> {

    /**
     * 根据sku的id集合查询SKU信息
     * @param ids
     * @return
     */
    List<Sku> findAllByIdIn(List<Long> ids);

}

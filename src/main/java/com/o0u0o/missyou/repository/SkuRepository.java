package com.o0u0o.missyou.repository;


import com.o0u0o.missyou.model.Sku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    /**
     * 减库存(乐观锁思想) 乐观并发控制
     * @param sid skuID
     * @param quantity 减少的数量
     * @return
     */
    @Modifying
    @Query("update Sku s set s.stock = s.stock - :quantity\n " +
            "where s.id = :sid\n " +
            "and s.stock >= :quantity")
    int reduceStock(Long sid, Long quantity);

    /**
     * 恢复库存
     * @param sid skuID
     * @param quantity 数量
     * @return
     */
    @Modifying
    @Query("update Sku s set s.stock = s.stock+(:quantity) where s.id = :sid")
    int recoverStock(@Param("sid") Long sid,
                     @Param("quantity") Long quantity);

}

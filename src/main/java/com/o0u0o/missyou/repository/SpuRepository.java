package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.Spu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName SpuRepository
 * @Author ChuanGui.Yue
 * @UpdateUser ChuanGui.Yue
 * @Date 2020/7/28 下午11:34
 * @Descripton: SPU 数据仓库
 * @Version: v0.0.1
 **/
public interface SpuRepository extends JpaRepository<Spu, Long> {

    Spu findOneById(Long id);

    /**
     * 根据二级分类ID查询 并通过创建时间进行倒叙排序
     * @param
     * @return: 
     * @author: ChuanGui.Yue
     * @date: 2020/8/2 下午3:20
     */
    Page<Spu> findByCategoryIdOrderByCreateTimeDesc(Long cid, Pageable pageable);

    /**
     * 根据根分类ID查询 并通过创建时间进行倒叙排序
     * @param
     * @return: 
     * @author: ChuanGui.Yue
     * @date: 2020/8/2 下午3:24
     */
    Page<Spu> findByRootCategoryIdOrderByCreateTimeDesc(Long id, Pageable pageable);
}

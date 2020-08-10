package com.o0u0o.missyou.service;

import com.o0u0o.missyou.model.Spu;
import org.springframework.data.domain.Page;


/**
 * @ClassName SpuService
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/28 下午11:22
 * @Descripton: xx接口
 * @Version: v0.0.1
 **/
public interface SpuService {

    Page<Spu> getLatestPagingSpu(Integer start, Integer count);

    Spu getSpu(Long id);

    Page<Spu> getByCategory(Long cid, Boolean isRoot, Integer pageNum, Integer PageSize);
}

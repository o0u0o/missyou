package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.model.Spu;
import com.o0u0o.missyou.repository.SpuRepository;
import com.o0u0o.missyou.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SpuServiceImpl
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/28 下午11:23
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    SpuRepository spuRepository;

    @Override
    public Page<Spu> getLatestPagingSpu(Integer pageNum, Integer pageSize){
        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("createTime").descending());
        return this.spuRepository.findAll(page);
    }

    @Override
    public Spu getSpu(Long id){
        return this.spuRepository.findOneById(id);
    }

    @Override
    public Page<Spu> getByCategory(Long cid, Boolean isRoot, Integer pageNum, Integer pageSize) {
        Pageable page = PageRequest.of(pageNum, pageSize);
        if (isRoot){
            return this.spuRepository.findByRootCategoryIdOrderByCreateTimeDesc(cid, page);
        }
        return this.spuRepository.findByCategoryIdOrderByCreateTimeDesc(cid, page);
    }

}

package com.o0u0o.missyou.service;

import com.o0u0o.missyou.model.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    /**
     * 获取所有分类
     * @return
     */
    Map<Integer, List<Category>> getAll();
}

package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.model.Category;
import com.o0u0o.missyou.repository.CategoryRepository;
import com.o0u0o.missyou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    /**
     * 获取所有分类
     * @return
     */
    public Map<Integer, List<Category>> getAll(){
        List<Category> roots = categoryRepository.findAllByIsRootOrderByIndexAsc(true);
        List<Category> subs = categoryRepository.findAllByIsRootOrderByIndexAsc(false);

        Map<Integer, List<Category>> categories = new HashMap<>();
        categories.put(1, roots);
        categories.put(2, subs);

        return categories;
    }

}

package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.model.GridCategory;
import com.o0u0o.missyou.repository.GridCategoryRepository;
import com.o0u0o.missyou.service.GridCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridCategoryServiceImpl implements GridCategoryService {

    @Autowired
    GridCategoryRepository gridCategoryRepository;

    @Override
    public List<GridCategory> getGaidCategoryList() {
        return gridCategoryRepository.findAll();
    }
}

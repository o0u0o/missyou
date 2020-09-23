package com.o0u0o.missyou.api.v1;

import com.o0u0o.missyou.service.GridCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("grid")
@RestController
@ResponseBody
public class GridCategoryController {

    @Autowired
    GridCategoryService gridCategoryService;



}

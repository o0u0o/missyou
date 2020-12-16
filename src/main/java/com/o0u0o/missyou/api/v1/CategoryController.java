package com.o0u0o.missyou.api.v1;

import com.o0u0o.missyou.core.http.NotFoundException;
import com.o0u0o.missyou.model.Category;
import com.o0u0o.missyou.model.GridCategory;
import com.o0u0o.missyou.service.CategoryService;
import com.o0u0o.missyou.service.GridCategoryService;
import com.o0u0o.missyou.vo.CategoriesAllVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryController
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/8/2 上午12:28
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
@RequestMapping("category")
@RestController
@ResponseBody
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    GridCategoryService gridCategoryService;

    /**
     * 获取所有分类
     * @return
     */
    @GetMapping("/all")
    public CategoriesAllVO getAll(){
        Map<Integer, List<Category>> categories = categoryService.getAll();
        return new CategoriesAllVO(categories);
    }

    @GetMapping("/grid/all")
    public List<GridCategory> getGridCategoryList(){
        List<GridCategory> gridCategoryList = gridCategoryService.getGridCategoryList();
        if (gridCategoryList.isEmpty()){
            throw new NotFoundException(30009);
        }
        return gridCategoryList;
    }

}

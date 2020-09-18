package com.o0u0o.missyou.vo;

import com.o0u0o.missyou.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class CategoriesAllVO {

    /** 一级分类 */
    private List<CategoryPureVO> roots;

    /** 二级分类 */
    private List<CategoryPureVO> subs;

    public CategoriesAllVO(Map<Integer, List<Category>> map) {
        this.roots = map.get(1).stream()
                .map(CategoryPureVO::new)
                .collect(Collectors.toList());
        this.subs = map.get(2).stream()
                .map(CategoryPureVO::new)
                .collect(Collectors.toList());
    }
}

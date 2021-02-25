package com.o0u0o.missyou.vo;

import com.o0u0o.missyou.model.Category;
import com.o0u0o.missyou.model.Coupon;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CouponCategoryVO
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2021/2/25 4:46 下午
 * @Descripton: 优惠券类目VO
 * @Version: v0.0.1
 **/
@Getter
@Setter
public class CouponCategoryVO extends CouponPureVO {

    private List<CategoryPureVO> categories = new ArrayList<>();

    public CouponCategoryVO(Coupon coupon){
        super(coupon);
        List<Category> categories = coupon.getCategoryList();
        categories.forEach(category ->{
            CategoryPureVO vo = new CategoryPureVO(category);
            this.categories.add(vo);
        });
    }
}

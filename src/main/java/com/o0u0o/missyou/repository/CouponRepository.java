package com.o0u0o.missyou.repository;


import com.o0u0o.missyou.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CouponRepository
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/17 8:48 上午
 * @Descripton: 优惠券仓库
 * @Version: v0.0.1
 **/
public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("select c from Coupon c \n" +
            " join c.categoryList ca \n" +
            " join Activity a on a.id = c.activityId \n" +
            " where ca.id = :cid " +
            " and a.startTime < :now \n" +
            " and a.endTime > :now \n")
    List<Coupon> findByCategory(Long cid, Date now);
}








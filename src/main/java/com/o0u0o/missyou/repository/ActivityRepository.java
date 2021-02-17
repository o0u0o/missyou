package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @ClassName ActivityRepository
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/15 6:18 下午
 * @Descripton: ActivityRepository接口
 * @Version: v0.0.1
 **/
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    /**
     * 根据活动名查询活动
     * @param name 活动名
     * @return
     */
    Activity findByName(String name);

    /**
     * 根据优惠券id查询活动列表
     * @param couponId 优惠券id
     * @return
     */
    Optional<Activity> findByCouponListId(Long couponId);
}

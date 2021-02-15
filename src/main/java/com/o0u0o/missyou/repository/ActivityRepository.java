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

    Activity findByName(String name);

    Optional<Activity> findByCouponListId(Long couponId);
}

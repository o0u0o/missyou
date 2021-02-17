package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.User;
import com.o0u0o.missyou.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @ClassName SpuRepository
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/17 1:12 下午
 * @Descripton: 用户优惠券 数据仓库
 * @Version: v0.0.1
 **/
@Repository
public interface UserCouponRepository  extends JpaRepository<UserCoupon, Long> {

    Optional<UserCoupon> findFirstByCouponId(Long uid, Long couponId);
}

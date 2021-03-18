package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

    /**
     * 根据用户id和优惠券id查询用户的优惠券
     * @param uid 用户id
     * @param couponId 优惠券id
     * @param status 优惠券状态 1-已经领取，但未使用
     * @return
     */
    Optional<UserCoupon> findFirstByUserIdAndCouponIdAndStatus(Long uid, Long couponId, int status);

    /**
     * 核销优惠券（要防止优惠券可以多次使用）
     * status-1:优惠券已领取未使用
     * orderId is null：优惠券未使用
     * @param couponId 优惠券id
     * @param oid 当前订单id
     * @param uid 用户id
     * @return
     */
    @Modifying
    @Query("update UserCoupon uc\n" +
            "set uc.status = 2, uc.orderId = :oid\n" +
            "where uc.userId = :uid\n " +
            "and uc.couponId = :couponId\n " +
            "and uc.status = 1 \n" +
            "and uc.orderId is null")
    int writeOff(Long couponId, Long oid, Long uid);
}

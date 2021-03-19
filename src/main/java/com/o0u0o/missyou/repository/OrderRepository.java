package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @ClassName OrderRepository
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/18 11:37 上午
 * @Descripton: 订单仓库接口
 * @Version: v0.0.1
 **/
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * 查询为支付订单
     * @param now 当前时间
     * @param status 状态
     * @param uid 用户id
     * @param pageable 分页参数
     */
    Page<Order> findByExpiredTimeGreaterThanAndStatusAndUserId(Date now, Integer status, Long uid, Pageable pageable);
}

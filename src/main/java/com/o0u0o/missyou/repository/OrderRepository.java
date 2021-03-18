package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}

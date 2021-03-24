package com.o0u0o.missyou.repository;

import com.o0u0o.missyou.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

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

    /**
     * 根据用户id查询
     * @param uid 用户id
     * @param pageable 分页对象
     * @return
     */
    Page<Order> findByUserId(Long uid, Pageable pageable);

    /**
     * 根据用户id和状态值查询
     * @param uid 用户id
     * @param status 订单状态
     * @param pageable 分页对象
     * @return
     */
    Page<Order> findByUserIdAndStatus(Long uid, Integer status, Pageable pageable);

    /**
     * 根据用户id和订单id查询
     * @param uid 用户id
     * @param oid 订单id
     * @return
     */
    Optional<Order> findFirstByUserIdAndId(Long uid, Long oid);

    /**
     * 根据订单号查询
     * @param orderNo - 订单号
     * @return
     */
    Optional<Order> findFirstByOrderNo(String orderNo);

    /**
     * 根据订单号更新订单状态
     * @param orderNo
     * @param status
     * @return
     */
    @Modifying
    @Query("update Order o set o.status=:status where o.orderNo=:orderNo")
    int updateStatusByOrderNo(String orderNo, Integer status);



}

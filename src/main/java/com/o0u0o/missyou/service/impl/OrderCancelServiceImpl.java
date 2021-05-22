package com.o0u0o.missyou.service.impl;

import com.o0u0o.missyou.bo.OrderMessageBO;
import com.o0u0o.missyou.core.exception.http.ServerErrorException;
import com.o0u0o.missyou.model.Order;
import com.o0u0o.missyou.repository.OrderRepository;
import com.o0u0o.missyou.repository.SkuRepository;
import com.o0u0o.missyou.service.OrderCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 订单取消业务实现类
 * @author o0u0o
 * @date 2021/5/20 7:51 下午
 */
@Service
public class OrderCancelServiceImpl implements OrderCancelService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SkuRepository skuRepository;

    /***
     * 取消订单
     */
    @Transactional
    @Override
    public void cancel(OrderMessageBO messageBO) {
        //1、校验订单号是否合法
        if (messageBO.getOrderId() <= 0){
            throw new ServerErrorException(9999);
        }
        this.cancel(messageBO.getOrderId());
    }

    /**
     * 取消订单
     * @param oid
     */
    private void cancel(Long oid){
        Optional<Order> orderOptional = orderRepository.findById(oid);
        Order order = orderOptional.orElseThrow(() -> new ServerErrorException(9999));
        //订单改为已取消状态
        int res = orderRepository.cancelOrder(oid);
        if (res != 1){
            return;
        }

        //恢复库存
        order.getSnapItems().stream().forEach(i ->{
            skuRepository.recoverStock(i.getId(), i.getCount().longValue());
        });
    }
}

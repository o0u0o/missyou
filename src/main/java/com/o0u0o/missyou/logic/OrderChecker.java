package com.o0u0o.missyou.logic;

import com.o0u0o.missyou.dto.OrderDTO;
import com.o0u0o.missyou.model.Sku;

import java.util.List;

/**
 * @ClassName OrderChecker
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Descripton: 订单检查逻辑s
 *           作用，内部需要调用CouponChecker
 *           调用关系（外观模式 facade）：
 *           OrderServer -> OrderChecker -> CouponChecker
 * @Date 2021/3/9 4:10 下午
 * @Version: v0.0.1
 **/
public class OrderChecker {

    private OrderDTO orderDTO;

    private List<Sku> serverSkuList;

    private CouponChecker couponChecker;


    public OrderChecker(OrderDTO orderDTO, List<Sku> serverSkuList, CouponChecker couponChecker){
        this.orderDTO = orderDTO;
        this.serverSkuList = serverSkuList;
        this.couponChecker = couponChecker;
    }

    public void isOk(){
        //校验orderTotalPrice 和 serverTotalPrice
        //校验sku是否下架
        //所购买的商品是否售罄
    }

}

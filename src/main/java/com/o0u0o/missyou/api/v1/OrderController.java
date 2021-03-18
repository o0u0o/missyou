package com.o0u0o.missyou.api.v1;


import com.o0u0o.missyou.core.LocalUser;
import com.o0u0o.missyou.core.interceptors.annotation.ScopeLevel;
import com.o0u0o.missyou.dto.OrderDTO;
import com.o0u0o.missyou.logic.CouponChecker;
import com.o0u0o.missyou.logic.OrderChecker;
import com.o0u0o.missyou.service.OrderService;
import com.o0u0o.missyou.vo.OrderIdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/25 4:57 下午
 * @Descripton: 订单路由
 * 1、对订单而言，校验的参数有哪些？
 *  （1）校验商品库存是否有货
 *  （2）
 * @Version: v0.0.1
 **/
@RestController
@RequestMapping("order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    /***
     * 下订单
     * @param orderDTO
     * @return
     */
    @ScopeLevel()
    @RequestMapping("")
    public OrderIdVO placeOrder(@RequestBody OrderDTO orderDTO){
        Long uid = LocalUser.getUser().getId();
        //订单校验和优惠券校验
        OrderChecker orderChecker = this.orderService.isOk(uid, orderDTO);

        //下订单
        this.orderService.placeOrder(uid, orderDTO, new CouponChecker());
        return null;
    }
}

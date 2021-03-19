package com.o0u0o.missyou.api.v1;


import com.o0u0o.missyou.bo.PageCounter;
import com.o0u0o.missyou.common.utils.CommonUtil;
import com.o0u0o.missyou.core.LocalUser;
import com.o0u0o.missyou.core.interceptors.annotation.ScopeLevel;
import com.o0u0o.missyou.dto.OrderDTO;
import com.o0u0o.missyou.logic.CouponChecker;
import com.o0u0o.missyou.logic.OrderChecker;
import com.o0u0o.missyou.model.Order;
import com.o0u0o.missyou.service.OrderService;
import com.o0u0o.missyou.vo.OrderIdVO;
import com.o0u0o.missyou.vo.OrderSimplifyVO;
import com.o0u0o.missyou.vo.PagingDozer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Value("${missyou.order.pay-time-limit}")
    private Long payTimeLimit;

    /***
     * 下订单
     * @param orderDTO 订单dto
     * @return
     */
    @ScopeLevel()
    @RequestMapping("")
    public OrderIdVO placeOrder(@RequestBody OrderDTO orderDTO){
        Long uid = LocalUser.getUser().getId();
        //订单校验和优惠券校验
        OrderChecker orderChecker = this.orderService.isOk(uid, orderDTO);

        //下订单
        Long oid = this.orderService.placeOrder(uid, orderDTO, orderChecker);
        return new OrderIdVO(oid);
    }

    /**
     * 待支付订单
     * @param start
     * @param count
     * @return
     */
    @ScopeLevel
    @GetMapping("/status/unpaid")
    public PagingDozer getUnpaidSimplifyList(@RequestParam(defaultValue = "0") Integer start,
                                             @RequestParam(defaultValue = "10") Integer count){
        PageCounter page = CommonUtil.convertToPageParameter(start, count);
        Page<Order> orderPage = this.orderService.getUnpaid(page.getPage(), page.getCount());
        PagingDozer<Order, OrderSimplifyVO> pagingDozer = new PagingDozer<>(orderPage, OrderSimplifyVO.class);
        //循环进行赋值
        pagingDozer.getItems()
                .stream()
                .forEach((o) -> ((OrderSimplifyVO)o).setPeriod(this.payTimeLimit));
        return pagingDozer;
    }
}

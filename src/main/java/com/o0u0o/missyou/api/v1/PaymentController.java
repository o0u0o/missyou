package com.o0u0o.missyou.api.v1;

import com.o0u0o.missyou.core.interceptors.annotation.ScopeLevel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.Map;

/**
 * @ClassName PaymentController
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/19 5:22 下午
 * @Descripton: 支付接口
 * @Version: v0.0.1
 **/
@RequestMapping("payment")
@RestController
@Validated
public class PaymentController {

    /**
     * 微信支付
     * @param oid 订单id
     */
    @PostMapping("/pay/order/{id}")
    @ScopeLevel
    public Map<String, String> preWxOrder(@PathVariable(name = "id") @Positive Long oid){

        return null;
    }
}

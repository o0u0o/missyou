package com.o0u0o.missyou.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.o0u0o.missyou.core.enumeration.OrderStatus;
import com.o0u0o.missyou.core.exception.http.ServerErrorException;
import com.o0u0o.missyou.model.Order;
import com.o0u0o.missyou.repository.OrderRepository;
import com.o0u0o.missyou.service.WxPaymentNotifyService;
import com.o0u0o.missyou.service.WxPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 微信在支付通知的业务实现类
 * @author o0u0o
 * @date 2021/3/24 10:25 上午
 */
@Service
public class WxPaymentNotifyServiceImpl implements WxPaymentNotifyService {

    @Autowired
    private WxPaymentServiceImpl WxPaymentServiceImpl;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * 处理支付回调通知
     * @param data
     */
    @Transactional
    @Override
    public void processPayNotify(String data) {
        Map<String, String> dataMap;
        try {
            dataMap = WXPayUtil.xmlToMap(data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }

        WXPay wxPay = WxPaymentServiceImpl.assembleWxPayConfig();
        boolean valid;
        try {
            valid = wxPay.isResponseSignatureValid(dataMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerErrorException(9999);
        }

        if (!valid){
            throw new ServerErrorException(9999);
        }

        String returnCode = dataMap.get("return_code");
        //orderNo这里的订单号是我们业务传递给微信的订单号，微信又回传过来的
        String orderNo = dataMap.get("out_trade_no");
        String resultCode = dataMap.get("result_code");

        if (!returnCode.equals("SUCCESS")){
            throw new ServerErrorException(9999);
        }

        if (!returnCode.equals("SUCCESS")){
            throw new ServerErrorException(9999);
        }

        if (orderNo == null){
            throw new ServerErrorException(9999);
        }

        this.deal(orderNo);
    }

    /**
     * 交易后需要处理的逻辑
     * @param orderNo 订单号
     */
    private void deal(String orderNo){
        Optional<Order> orderOptional = this.orderRepository.findFirstByOrderNo(orderNo);
        Order order = orderOptional.orElseThrow(() -> new ServerErrorException(9999));

        int res = -1;
        //如果订单为取消状态，订单
        if (order.getStatus().equals(OrderStatus.UNPAID.value())
                || order.getStatus().equals(OrderStatus.CANCELED.value())){
            //1、支付状态 unpaid -> paid
            res = this.orderRepository.updateStatusByOrderNo(orderNo, OrderStatus.PAID.value());
        }
        if (res != 1){
            throw new ServerErrorException(9999);
        }

    }

}

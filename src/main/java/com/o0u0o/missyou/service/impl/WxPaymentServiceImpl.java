package com.o0u0o.missyou.service.impl;

import com.github.wxpay.sdk.O0u0oWxPayConfig;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.o0u0o.missyou.common.utils.CommonUtil;
import com.o0u0o.missyou.common.utils.HttpRequestProxy;
import com.o0u0o.missyou.core.LocalUser;
import com.o0u0o.missyou.core.exception.http.ForbiddenException;
import com.o0u0o.missyou.core.exception.http.NotFoundException;
import com.o0u0o.missyou.core.exception.http.ParameterException;
import com.o0u0o.missyou.core.exception.http.ServerErrorException;
import com.o0u0o.missyou.model.Order;
import com.o0u0o.missyou.repository.OrderRepository;
import com.o0u0o.missyou.service.OrderService;
import com.o0u0o.missyou.service.WxPaymentService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName WxPaymentServiceImpl
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/19 5:28 下午
 * @Descripton: 微信支付业务实现类
 * @Version: v0.0.1
 **/
@Service
public class WxPaymentServiceImpl implements WxPaymentService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Value("${missyou.order.pay-callback-host}")
    private String payCallbackHost;

    @Value("${missyou.order.pay-callback-path}")
    private String payCallbackPath;

    private static O0u0oWxPayConfig o0u0oWxPayConfig = new O0u0oWxPayConfig();

    /**
     * 预订单业务
     * 1、一气呵成，调用placeOrder 用户立即支付 -> 订单未过期
     * 2、历史订单，调用placeOrder 用户再支付 -> 订单可能未过期，也可能已过期
     * @param oid 订单号
     * @return
     */
    @Override
    public Map<String, String> preOrder(Long oid) {
        //1、查询订单号是否存在
        Long uid = LocalUser.getUser().getId();
        Optional<Order> optionalOrder = this.orderRepository.findFirstByUserIdAndId(uid, oid);
        Order order = optionalOrder.orElseThrow(() -> new NotFoundException(50009));
        if(order.needCancel()){
            throw new ForbiddenException(50010);
        }

        //2、调用微信服务器
        WXPay wxPay = this.assembleWxPay();
        Map<String, String> params = this.makePreOrderParams(order.getFinalTotalPrice(), order.getOrderNo());
        Map<String, String> wxOrder;
        //2.1 统一下单
        try {
            wxOrder = wxPay.unifiedOrder(params);
        } catch (Exception e) {
            throw new ServerErrorException(9999);
        }
        //2.2 调用成功微信支付会返回结果 对结果进行处理 （记录下wxOrder的prepay_id 以便未支付订单下次继续使用）
        if (this.unifiedOrderSuccess(wxOrder)){
            this.orderService.updateOrderPrepayId(order.getId(), wxOrder.get("prepay_id"));
        }

        //2.3 返回签名
        return this.makePaySignature(wxOrder);
    }

    /**
     * 装配WxPay
     * 配置商户号、秘钥、超时时间等
     * @return
     */
    private WXPay assembleWxPay(){
        WXPay wxPay;
        try {
            wxPay = new WXPay(WxPaymentServiceImpl.o0u0oWxPayConfig);
        } catch (Exception e){
            throw new ServerErrorException(9999);
        }
        return wxPay;
    }


    /**
     * 生成统一下单参数
     * 配置订单相关的一些信息
     * @param serverFinalPrice 最终支付价格
     * @param orderNo 商户订单号
     * @return
     */
    private Map<String, String> makePreOrderParams(BigDecimal serverFinalPrice,
                                                   String orderNo){
        String payCallbackUrl = this.payCallbackHost + this.payCallbackPath;
        Map<String, String> data = new HashMap<>();
        //商品的标题
        data.put("body", "Sleeve");
        //商户订单号（商户业务的订单号）
        data.put("out_trade_no", orderNo);
        //设备类型
        data.put("device_info", "Sleeve");
        //货币类型 CNY
        data.put("free_type", "CNY");
        //微信支付类型
        data.put("trade_type", "JSAPI");

        //订单的支付金额
        data.put("total_fee", CommonUtil.yuanToFenPlainString(serverFinalPrice));
        //微信的open_id
        data.put("open_id", LocalUser.getUser().getOpenid());
        //当前用户的客户端的IP
        data.put("spbill_create_ip", HttpRequestProxy.getRemoteRealIp());
        //支持成功后回调地址（需要提供外网的IP地址）
        data.put("notify_url", payCallbackUrl);
        return data;
    }


    /**
     * 统一下单成功
     * @param wxOrder 请求微信支付响应的数据
     * @return
     */
    private Boolean unifiedOrderSuccess(Map<String, String> wxOrder){
        if (!wxOrder.get("return_code").equals("SUCCESS")
                || !wxOrder.get("result_code").equals("SUCCESS")) {
            throw new ParameterException(10007);
        }
        return true;
    }

    /**
     * 生成微信签名
     * @param wxOrder
     * @return
     */
    private Map<String, String> makePaySignature(Map<String, String> wxOrder){
        Map<String, String> wxPayMap = new HashMap<>();
        String packages = "prepay_id="+wxOrder.get("prepay_id");

        wxPayMap.put("appId", WxPaymentServiceImpl.o0u0oWxPayConfig.getAppID());

        //时间戳 微信要得是10位的时间戳
        wxPayMap.put("timeStamp", CommonUtil.timestamp10());
        //随机字符串
        wxPayMap.put("nonceStr", RandomStringUtils.randomAlphanumeric(32));
        //统一下单接口返回的prepay_id 的参数值
        wxPayMap.put("package", packages);
        //签名方式（微信支付默认为MD5） HMAC-SHA256
        wxPayMap.put("signType", "HMAC-SHA256");

        //使用微信SDK生成签名
        String sign;
        try {
            sign = WXPayUtil.generateSignature(wxPayMap, WxPaymentServiceImpl.o0u0oWxPayConfig.getKey(), WXPayConstants.SignType.HMACSHA256);
        } catch (Exception e){
            throw new ServerErrorException(9999);
        }

        //小程序支付的参数
        Map<String, String> miniPayParams = new HashMap<>();
        miniPayParams.put("paySign", sign);
        miniPayParams.putAll(wxPayMap);
        miniPayParams.remove("appId");
        return miniPayParams;
    }

    /**
     * 组装微信支付配置
     * @return
     */
    public WXPay assembleWxPayConfig(){
        WXPay wxPay;
        try {
            wxPay = new WXPay(WxPaymentServiceImpl.o0u0oWxPayConfig);
        } catch (Exception e){
            throw new ServerErrorException(9999);
        }
        return wxPay;
    }
}

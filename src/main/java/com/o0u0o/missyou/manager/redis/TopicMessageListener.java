package com.o0u0o.missyou.manager.redis;

import com.o0u0o.missyou.bo.OrderMessageBO;
import com.o0u0o.missyou.service.CouponBackService;
import com.o0u0o.missyou.service.OrderCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * 监听器
 * 用于监听接受redis键空间通知 需要实现MessageListener接口
 * @author o0u0o
 * @date 2021/5/20 2:20 下午
 */
public class TopicMessageListener implements MessageListener {

    @Autowired
    private OrderCancelService orderCancelService;

    @Autowired
    private CouponBackService couponBackService;

    /**
     * 获取redis键空间通知
     * @param message
     * @param bytes
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        //去到redis回传的数据
        byte[] body = message.getBody();
        //通过massage拿到channel信息
        byte[] channel = message.getChannel();
        //转化为String类型
        String expiredKey = new String(body);
        String topic = new String(channel);

        OrderMessageBO orderMessageBO = new OrderMessageBO(expiredKey);
        //取消订单
        orderCancelService.cancel(orderMessageBO);
        //归还优惠券
        couponBackService.returnBack(orderMessageBO);
    }
}

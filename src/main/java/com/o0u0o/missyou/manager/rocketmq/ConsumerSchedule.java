package com.o0u0o.missyou.manager.rocketmq;

import com.o0u0o.missyou.bo.OrderMessageBO;
import com.o0u0o.missyou.service.CouponBackService;
import com.o0u0o.missyou.service.OrderCancelService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 消费者
 * @author o0u0o
 * @date 2021/5/22 7:29 下午
 */
@Component
public class ConsumerSchedule implements CommandLineRunner {

    @Value("${rocketmq.consumer.consumer-group}")
    private String producerGroup;

    @Value("${rocketmq.namesrv-addr}")
    private String namesrvAddr;

    @Autowired
    private OrderCancelService orderCancelService;

    @Autowired
    private CouponBackService couponBackService;

    public void messageListener() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(producerGroup);
        consumer.setNamesrvAddr(namesrvAddr);

        consumer.subscribe("TopicTest", "*");

        consumer.setConsumeMessageBatchMaxSize(1);

        //注册监听消息
        consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
            for (Message message : messages){
                System.out.println("消息:" + new String(message.getBody()));
            }
            //todo 处理业务

            //消费成功返回
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        //消费者启动
        consumer.start();
    }


    @Override
    public void run(String... args) throws Exception {
        //触发messageListener
        this.messageListener();
    }
}

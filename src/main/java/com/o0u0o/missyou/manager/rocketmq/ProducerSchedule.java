package com.o0u0o.missyou.manager.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * 有计划的生产者
 * @author o0u0o
 * @date 2021/5/22 9:21 上午
 */
@Component
public class ProducerSchedule {

    private DefaultMQProducer producer;

    @Value("${rocketmq.producer.producer-group}")
    private String producerGroup;

    @Value("${rocketmq.namesrv-addr}")
    private String namesrvAddr;

    //构造函数中无法取到相关的配置值
    public ProducerSchedule() {
    }

    /**
     * 参数实例化
     * 方法由SpringBoot进行调用
     */
    @PostConstruct
    public void defaultMQProducer(){
        if (this.producer == null){
            this.producer = new DefaultMQProducer(this.producerGroup);
            this.producer.setNamesrvAddr(this.namesrvAddr);
        }
        try{
            this.producer.start();
            System.out.println("------ producer start ------");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息队列
     * @param topic
     * @param messageText
     * @return
     */
    public String send(String topic, String messageText) throws Exception{
        Message message = new Message(topic, messageText.getBytes());
        //设置延迟消息的级别 如：默认消息级别（9代表5分钟） 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m等
        message.setDelayTimeLevel(4);

        SendResult result = this.producer.send(message);
        System.out.println(result.getMsgId() +" "+ result.getSendStatus());

        return result.getMsgId();
    }
}

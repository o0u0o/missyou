package com.o0u0o.missyou.manager.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
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
}

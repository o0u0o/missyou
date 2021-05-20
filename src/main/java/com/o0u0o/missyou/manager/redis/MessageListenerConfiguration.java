package com.o0u0o.missyou.manager.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * 消息监听配置类
 * 注解 @Configuration 注解标记为Configuration
 * @author o0u0o
 * @date 2021/5/20 2:38 下午
 */
@Configuration
public class MessageListenerConfiguration {

    @Value("${spring.redis.listen-pattern}")
    public String pattern;

    /**
     * 使用注解@Bean返回的对象会纳入IOC容器
     * @return 返回RedisMessageListenerContainer
     */
    @Bean
    public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory redisConnection){
        //return new TopicMessageListener();
        //con 负责连接redis服务器，以及TopicMessageListener绑定到监听里
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnection);

        //设置监听主题
        PatternTopic topic = new PatternTopic(this.pattern);

        container.addMessageListener(new TopicMessageListener(), topic);
        return container;
    }

}

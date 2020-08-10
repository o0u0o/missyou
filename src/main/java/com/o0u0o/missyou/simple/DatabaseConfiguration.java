package com.o0u0o.missyou.simple;

import com.o0u0o.missyou.simple.database.IConnect;
import com.o0u0o.missyou.simple.database.MySQL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author aiuiot
 * @Date 2020/3/15 12:31 下午
 * @Descripton: 数据库配置
 **/
@Configuration
public class DatabaseConfiguration {

    //@Value("${mysql.ip}")
    private String ip;

    //@Value("${mysql.port}")
    private Integer port;

    @Bean
    public IConnect mysql(){
        return new MySQL(this.ip, this.port);
    }

}

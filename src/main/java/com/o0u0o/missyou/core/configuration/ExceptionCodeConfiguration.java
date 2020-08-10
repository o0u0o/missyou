package com.o0u0o.missyou.core.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ExceptionCodeConfiguration
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/9 上午1:08
 * @Descripton: 错误码配置类
 * 1、用于读取exception-code.yum的配置文件
 * 2、因为配置文件是键值对形式 使用Map结构方便读取
 * 3、将类和配置文件关联 使用注解 @PropertySource(value = "classpath:config/exception-code.properties") 指定配置文件的源文件
 * 4、设置配置文件读取的前缀 @ConfigurationProperties(prefix = "lin")
 * 5、将该类加入 IOC 容器 @Component
 * 6、设置Get Set 方法 @Data
 * @Version: v0.0.1
 **/
@Data
@ConfigurationProperties(prefix = "lin")
@PropertySource(value = "classpath:config/exception-code.properties")
@Component
public class ExceptionCodeConfiguration {


    private Map<Integer, String> codes = new HashMap<>();

    /**
     * 通过code码获取message
     * @param code 错误码
     * @return
     */
    public String getMessage(Integer code){
        return codes.get(code);
    }

}

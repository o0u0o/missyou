package com.o0u0o.missyou.api.v1;

import com.o0u0o.missyou.simple.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/10 10:34 上午
 * @Descripton: 测试控制器
 * @Version: v0.0.1
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ObjectFactory<Test> test;

    @GetMapping("")
    public void getDetail(Test test){
        System.out.println(this.test);
    }

    /**
     * 发布消息到mq
     */
    public void pushMessageToMQ(){

    }
}

package com.o0u0o;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author aiuiot
 * @Date 2020/4/5 12:35 下午
 * @Descripton:
 **/
//@RestController
//@RequestMapping("/hello")
public class HelloController {

    @GetMapping(value = "/world")
    public String tst(){
        return "hello world!";
    }
}

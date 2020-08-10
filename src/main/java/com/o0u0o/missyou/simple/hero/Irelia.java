package com.o0u0o.missyou.simple.hero;

import com.o0u0o.missyou.simple.ISkill;
import org.springframework.stereotype.Component;

/**
 * @Author aiuiot
 * @Date 2020/2/17 11:05 下午
 * @Descripton:
 **/
//@Component
public class Irelia implements ISkill {

    public Irelia(){
        System.out.println("Irelia 被实例化");
    }

    @Override
    public void q() {
        System.out.printf("Irelia Q");
    }

    @Override
    public void w() {
        System.out.printf("Irelia W");
    }

    @Override
    public void e() {
        System.out.printf("Irelia E");

    }

    @Override
    public void r() {
        System.out.printf("Irelia R");
    }
}

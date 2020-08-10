package com.o0u0o.missyou.simple.hero;

import com.o0u0o.missyou.simple.ISkill;

/**
 * @Author aiuiot
 * @Date 2020/3/15 11:43 上午
 * @Descripton: Camille 英雄
 **/
public class Camille implements ISkill {

    private String name;

    private Integer age;

    public Camille(String name, Integer age){
        this.name = name;
        this.age = age;
    }
    @Override
    public void q(){
        System.out.println("Camille Q");
    }

    @Override
    public void w(){
        System.out.println("Camille W");
    }

    @Override
    public void e(){
        System.out.println("Camille E");
    }

    @Override
    public void r(){
        System.out.println("Camille R");
    }
}

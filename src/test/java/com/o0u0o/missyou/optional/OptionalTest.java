package com.o0u0o.missyou.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Optional测试
 * @author mac
 * @date 2020/12/16 4:09 下午
 */
public class OptionalTest {

    /**
     * Optional精髓：
     * 1、取值时报错的好处，方便空指针异常排查溯源
     * 2、空指针是一种隐藏性的错误，随着函数调用栈，bug就会隐藏得很深，溯源调试及其不方便解决。
     */
    @Test
    public void testOptional(){
        //构件一个空的Optional对象
        Optional<String> empty = Optional.empty();
        //of默认需要传入非空的值
//        Optional<String> t1 = Optional.of(null);

        //不确定传入的是否为空 就用ofNullable
        Optional<String> t2 = Optional.ofNullable(null);
//        empty.get();
//        return empty;

        //如果一个对象是Optional对象 直接取值就会报错
//        String s = t2.get();

        // 有值返回true 这种方式使用没啥意义
        if (t2.isPresent()){

        } else {

        }
        //t2 如果为空，不会执行
        t2.ifPresent(System.out::println);

        String s = t2.orElse("默认值");
        System.out.println(s);

//        Consumer 消费者
//        Supplier 提供者
//        Supplier<String> supplier = "123";
//        String s1 = t2.orElseGet();

        String s1 = t2.map(t -> t + "b").orElse("c");
        System.out.println(s1);

    }

}

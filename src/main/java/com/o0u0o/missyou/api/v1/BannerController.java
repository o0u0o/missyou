package com.o0u0o.missyou.api.v1;

import com.o0u0o.missyou.core.exception.http.NotFoundException;
import com.o0u0o.missyou.core.interceptors.annotation.ScopeLevel;
import com.o0u0o.missyou.model.Banner;
import com.o0u0o.missyou.service.BannerService;
import com.o0u0o.missyou.core.exception.http.ForbiddenException;
import com.o0u0o.missyou.dto.PersonDTO;
import com.o0u0o.missyou.simple.ISkill;
import com.o0u0o.missyou.simple.database.IConnect;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @Author aiuiot
 * @Date 2019/12/11 11:08 上午
 * @Descripton: Banner 控制器
 *
 *  一般使用 @Component + @Autowired
 *  注解讲解：
 *      注解 @Lazy 延迟对象/Bean实例化
 **/
@RestController
//@Lazy

// 优先的
//@Primary
@RequestMapping("/banner")
public class BannerController {

    /**
     * 被动推断注入：
     * 通过 @Autowired 将类注入
     * 报黄线是idea 推荐时候构造函数进行注入
     * 属性注入方式，其实不规范 但快捷方便
     *
     * 主动注入：
     * 通过追加 @Qualifier("irelia") 注解
     * 主动注入irelia Bean的名字
     */
    @Autowired
    //@Qualifier("irelia")
    private ISkill camille;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private IConnect iConnect;

    /**
     * 1、懒加载
     * 2、急加载
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    @ScopeLevel()
    public Banner getByName(@PathVariable @NotBlank String name){
        Banner banner = bannerService.getByName(name);
        if (banner == null){
            throw new NotFoundException(30005);
        }
        return banner;
    }

    
    //构造函数注入(推荐)
//    @Autowired
//    private BannerController(Diana diana){
//        this.diana = diana;
//    }=

    //Setter方式注入
//    @Autowired
//    public Diana getDiana() {
//        return diana;
//    }

    @GetMapping(value = "/hello")
    public String tst() throws Exception {
        camille.r();
        throw new ForbiddenException(10001);
//        throw new NotFoundException(10001);
//        throw new RuntimeException("这里错了");
        //return "hello";
    }

    @GetMapping("/test/{id}")
    public void test(@PathVariable(name = "id") Integer id){
        System.out.println(id);
        iConnect.connect();
    }

    @GetMapping("/test2")
    public void test2(@RequestParam String name){
        System.out.println(name);
    }

    /**
     * 通常不用Map集合
     * 有个装箱的过程，尽量避免装箱
     * @param person
     */
    @PostMapping("/test3")
    public void test3(@RequestBody Map<String, Object> person){
        System.out.println(person);
    }


    /**
     * vo
     * bo
     * 一、@Validated 和 @Valid 区别：
     * 1、两者具有一定相似性@Validated 属于Spring框架 @Valid 是Java中的
     * 2、开启验证时建议使用@Validated
     *
     * 二、自定义校验（如验证密码的自动校验）
     *
     * 三、更多注解网上搜索 Hibernate Valid(JSR303)
     * @param person
     */
    @PostMapping("/test4")
    public PersonDTO test4(@RequestBody @Validated PersonDTO person){
        //PersonDTO personDTO = new PersonDTO();
        //personDTO.setAge(19);
        //PersonDTO dto = new PersonDTO(person.getName(), person.getAge());
        PersonDTO dto = PersonDTO.builder().name(person.getName()).age(18).build();
        System.out.println(person);
//        return personDTO;
        return dto;
    }
}

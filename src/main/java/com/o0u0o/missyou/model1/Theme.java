//package com.o0u0o.missyou.model1;
//
//import javax.persistence.*;
//import java.util.List;
//
///**
// * @ClassName Theme
// * @Author aiuiot
// * @UpdateUser aiuiot
// * @Date 2020/7/23 下午11:37
// * @Descripton: 主题实体类
// * @Version: v0.0.1
// **/
//@Entity
//public class Theme {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String title;
//
//    private String name;
//
//    /**
//     * 导航属性配置
//     * 1、注解 @JoinTable(xxx) 指定第三张生成表的规范
//     *  1.1、指定生成表名称 name = "theme_spu"
//     *  1.2、指定外键名称 joinColumns = @JoinColumn(name = "theme_id")
//     */
//    @ManyToMany
//    @JoinTable(name = "theme_spu", joinColumns = @JoinColumn(name = "theme_id"), inverseJoinColumns = @JoinColumn(name = "spu_id"))
//    private List<Spu> spuList;
//}

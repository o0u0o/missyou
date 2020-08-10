//package com.o0u0o.missyou.model1;
//
//
//import javax.persistence.*;
//import java.util.List;
//
///**
// * @ClassName Banner
// * @Author aiuiot
// * @UpdateUser aiuiot
// * @Date 2020/7/15 下午11:26
// * @Descripton: 轮播模型
// * @Version: v0.0.1
// **/
//@Entity
////@Table(name = "banner1")
//public class Banner {
//
//    /** 主键ID */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    /** 轮播名 */
//    @Column(length = 16)
//    private String name;
//
//    /** 描述  @Transient 不生成到数据库表 */
//    @Transient
//    private String description;
//
//    /** 图片URL */
//    private String img;
//
//    /** 标题 */
//    @Column(length = 32)
//    private String title;
//
//    /** 一对多   1、急加载：@OneToMany(fetch = FetchType.EAGER)*/
//    /**
//     * 1、在1方加入注解 禁止JPA生成物理外键 @org.hibernate.annotations.ForeignKey(name = "null")
//     */
////    @OneToMany
//    @OneToMany(mappedBy = "banner", fetch = FetchType.EAGER)
////    @org.hibernate.annotations.ForeignKey(name = "null")
//    private List<BannerItem> items;
//}

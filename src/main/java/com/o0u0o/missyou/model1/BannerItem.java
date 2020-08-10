//package com.o0u0o.missyou.model1;
//
//import javax.persistence.*;
//
///**
// * @ClassName BannerItem
// * @Author aiuiot
// * @UpdateUser aiuiot
// * @Date 2020/7/16 下午10:50
// * @Descripton: 轮播Item
// * 1、设置主键子增长  @GeneratedValue(strategy =  GenerationType.IDENTITY)
// * @Version: v0.0.1
// **/
//@Entity
//public class BannerItem {
//
//    @Id
//    @GeneratedValue(strategy =  GenerationType.IDENTITY)
//    private Long id;
//
//    /** 轮播图片URL */
//    private String img;
//
//    /** 跳转页面时 携带的关键字 可能是专题标示，也可能是商品ID */
//    private String keyword;
//
//    /** 用户点击轮播时，跳转类型 */
//    private Short type;
//
//    private String name;
//
//    /**
//     * Repeated column in mapping for entity: com.o0u0o.missyou.model.BannerItem column: banner_id (should be mapped with insert="false" update="false")
//     * 1、将bannerId注释掉，不能显式写出，但是数据库表会给我们隐式生成
//     * 2、可以显式表达 但是需要配置关闭可插入和可更新配置 insertable = false, updatable = false,
//     */
//
//    private Long bannerId;
//
//    /**
//     * 单向一对多
//     * 双向一对多
//     */
//    @ManyToOne
//    @JoinColumn(insertable = false, updatable = false, name="bannerId")
//    private Banner banner;
//
//}

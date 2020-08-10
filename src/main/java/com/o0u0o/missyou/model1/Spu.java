//package com.o0u0o.missyou.model1;
//
//import javax.persistence.*;
//import java.util.List;
//
///**
// * @ClassName Spu
// * @Author aiuiot
// * @UpdateUser aiuiot
// * @Date 2020/7/23 下午11:37
// * @Descripton: Spu实体类
// * @Version: v0.0.1
// **/
//@Entity
//public class Spu {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    /** 标题 */
//    private String title;
//
//    /** 副标题 */
//    private String subTitle;
//
//    /**
//     * 双向多对多的导航属性配置
//     * 1、mappedBy = "spuList" 关系的维护端为 spuList(Theme表的导航名称)
//     * 2、关系维护端和被维护端对查询影响不大，但是对更新、删除影响很大，不建议使用JPA来做删除和更新，因为会涉及到级联问题(去孤子问题)
//     * 3、大多数项目不会物理删除数据，一般采用软删除，因为数据具有一定的商业价值
//     */
//    @ManyToMany(mappedBy = "spuList")
//    private List<Theme> themeList;
//}

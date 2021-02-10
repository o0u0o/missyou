package com.o0u0o.missyou.model;

import java.util.Map;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Where(clause = "delete_time is null")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 微信openid */
    private String openid;

    /** 昵称 */
    private String nickname;

    /** 微信uniid */
    private Integer unifyUid;

    /** 电子邮箱 */
    private String email;

    /** 密码 建议把权限放在另外一张表 */
    private String password;

    /** 电话号 */
    private String mobile;

    /**  用户组 青铜、砖石... */
//    private String group;

    /** 优惠券 */
    //private List<Coupon> couponList;

    /** 用户微信资料 */
    @Convert(converter = MapAndJson.class)
    private Map<String, Object> wxProfile;

}


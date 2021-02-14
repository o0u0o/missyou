package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Activity
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/11 下午4:27
 * @Descripton: 活动实体
 * @Version: v0.0.1
 **/
@Entity
@Getter
@Setter
@Where(clause = "delete_time is null and online = 1")
public class Activity extends BaseEntity {

    /** 主键ID */
    @Id
    private Integer id;

    /** 活动标题 */
    private String title;

    /** 活动名称 */
    private String name;

    /** 描述 */
    private String description;

    /** 开始时间 */
    private Date startTime;

    /** 结束时间 */
    private Date endTime;

    /** 是否在线 */
    private Boolean online;

    /** 活动入口的图片 */
    private String entranceImg;

    /** 活动顶部图片 */
    private String internalTopImg;

    /** 备注 */
    private String remark;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "activityId")
    private List<Coupon> couponList;
}


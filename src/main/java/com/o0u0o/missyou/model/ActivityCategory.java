package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName ActivityCategory
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/11 下午6:04
 * @Descripton: 活动分类
 * @Version: v0.0.1
 **/
@Entity
@Getter
@Setter
public class ActivityCategory {

    /** 主键ID */
    @Id
    private Integer id;

    /** 分类ID */
    private Integer categoryId;

    /** 活动ID */
    private Integer activityId;
}


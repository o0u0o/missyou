package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName ActivitySpu
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/11 下午6:07
 * @Descripton: 商品SPU活动
 * @Version: v0.0.1
 **/
@Entity
@Getter
@Setter
public class ActivitySpu {

    /** 主键ID */
    @Id
    private Integer id;

    /** 活动ID */
    private Integer activityId;

    /**
     *
     */
    private Integer spuId;

    /**
     *
     */
    private Byte participation;
}


package com.o0u0o.missyou.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/27 下午10:43
 * @Descripton: 描述xx类
 * 1、忽略前端显示的字段 使用注解 @JsonIgnore 忽略序列化读取-前端不会被显式
 * 2、需要和数据库映射需要加入注解 @MappedSuperclass 标注为Entity的父类
 * @Version: v0.0.1
 **/

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    /** 创建时间 */
    @JsonIgnore
    private Date createTime;

    /** 更新时间 */
    @JsonIgnore
    private Date updateTime;

    /** 删除时间 */
    @JsonIgnore
    private Date deleteTime;
}

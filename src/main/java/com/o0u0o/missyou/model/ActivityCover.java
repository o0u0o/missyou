package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import org.hibernate.annotations.Where;

import java.util.List;

/**
 * @ClassName ActivityCover
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/16 6:34 下午
 * @Descripton: 活动模板
 * @Version: v0.0.1
 **/
@Entity
@Getter
@Setter
@Where(clause = "delete_time is null and online = 1")
public class ActivityCover extends BaseEntity{
    @Id
    private Long id;
    private String coverImg;
    private String internalTopImg;
    private String name;
    private String title;
    private String description;
    private Boolean online;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="activityCoverId")
    private List<Activity> activityList;
}

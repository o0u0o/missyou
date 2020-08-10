package com.o0u0o.missyou.model1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName TagEntity
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/27 下午10:15
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
@Getter
@Setter
@Entity
public class Tag {

    @Id
    private Long id;

    private String title;

    private String description;

    private Date updateTime;

    private Date deleteTime;

    private Date createTime;

    private Byte highlight;

    private Byte type;
}

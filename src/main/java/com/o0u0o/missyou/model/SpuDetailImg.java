package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName SpuDetailImg
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/29 下午9:31
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
@Setter
@Getter
@Entity
public class SpuDetailImg extends BaseEntity {

    @Id
    private Long id;

    private String img;

    private Long spuId;

    /** 排序 */
    private Long index;
}

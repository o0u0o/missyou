package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * TODO
 *
 * @author mac
 * @date 2020/11/10 8:47 下午
 */
@Entity
@Getter
@Setter
@Where(clause = "delete_time is null")
public class Theme extends BaseEntity {

    @Id
    private Long id;
    private String title;
    private String description;
    private String name;
    private String extend;
    private String entranceImg;
    private String internalTopImg;
    private String online;
    private String titleImg;

    /** 模板名称 */
    private String tplName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "theme_spu", joinColumns = @JoinColumn())
    private List<Spu> spuList;


}

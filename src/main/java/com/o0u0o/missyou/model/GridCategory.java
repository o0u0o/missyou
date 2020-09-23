package com.o0u0o.missyou.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Where(clause = "delete_time is null")
public class GridCategory extends BaseEntity {

    @Id
    private Long id;

    private String title;

    private String img;

    private String name;

    /** 分类 */
    private Long categoryId;

    /** 根分类id */
    private Long rootCategoryId;
}

package com.o0u0o.missyou.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName Paging
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/30 下午10:59
 * @Descripton: 分页VO
 * 1、无参构造方法注解 @NoArgsConstructor
 * @Version: v0.0.1
 **/
@Getter
@Setter
@NoArgsConstructor
public class Paging<T> {

    /** 总数量 */
    private Long total;

    /** 数量 */
    private Integer count;

    /**  */
    private Integer page;

    /** 总页数 */
    private Integer totalPage;

    /** 数据 */
    private List<T> items;



    /**
     * 构造方法
     * @param page
     */
    public Paging(Page<T> page){
        //初始化查询参数
        this.initPageParameters(page);
        //获取数据库查询的数据
        this.items = page.getContent();
    }

    /**
     * 初始化参数
     * @param page
     */
    void initPageParameters(Page<T> page){
        this.total = page.getTotalElements();
        this.count = page.getSize();
        this.page = page.getNumber();
        this.totalPage = page.getTotalPages();
    }
}

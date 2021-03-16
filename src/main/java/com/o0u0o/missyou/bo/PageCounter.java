package com.o0u0o.missyou.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName PageCounter
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2020/7/30 下午10:50
 * @Descripton: 分页 业务层对象
 * @Version: v0.0.1
 **/
@Getter
@Setter
@Builder
public class PageCounter {

    private Integer page;

    private Integer count;
}

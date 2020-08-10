package com.o0u0o.missyou.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName PageCounter
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/30 下午10:50
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
@Getter
@Setter
@Builder
public class PageCounter {

    private Integer page;

    private Integer count;
}

package com.o0u0o.missyou.common.utils;

import com.o0u0o.missyou.bo.PageCounter;

/**
 * @ClassName CommonUtil
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/30 下午10:44
 * @Descripton: 描述xx类
 * @Version: v0.0.1
 **/
public class CommonUtil {

    /**
     * 将start count 转换为 pageNum pageSize
     * @param start
     * @param count
     * @return
     */
    public static PageCounter convertToPageParameter(Integer start, Integer count){
        int pageNum = start / count;

        PageCounter pageCounter = PageCounter.builder()
                .page(pageNum)
                .count(count)
                .build();
        return pageCounter;
    }
}

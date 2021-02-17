package com.o0u0o.missyou.common.utils;

import com.o0u0o.missyou.bo.PageCounter;

import java.util.Date;

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

    /**
     * 判断是否在时间段内
     * @param date 当前时间
     * @param start 开始时间
     * @param end 结束时间
     * @return
     */
    public static Boolean isInTimeLine(Date date, Date start, Date end){
        Long time = date.getTime();
        Long startTime = start.getTime();
        Long endTime = end.getTime();
        if (time > startTime && time < endTime){
            return true;
        }
        return false;
    }
}

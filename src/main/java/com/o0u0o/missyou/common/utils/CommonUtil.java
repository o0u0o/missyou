package com.o0u0o.missyou.common.utils;

import com.o0u0o.missyou.bo.PageCounter;

import java.math.BigDecimal;
import java.util.Calendar;
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
     * 计算过期时间
     * @param calendar 当前的时间
     * @param seconds 多少秒后过期
     * @return
     */
    public static Calendar addSomeSeconds(Calendar calendar, int seconds){
        calendar.add(Calendar.SECOND, seconds);
        return calendar;
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

    /**
     * 是否已经过期
     * @param startTime 开始时间
     * @return period 过期秒数(单位：秒)
     */
    public static Boolean isOutOfDate(Date startTime, Long period){
        Long now = Calendar.getInstance().getTimeInMillis();
        Long startTimeStamp = startTime.getTime();
        //转换成毫秒
        Long periodMillSecond = period * 1000;
        if (now > (startTimeStamp + periodMillSecond)){
            return true;
        }
        return false;
    }

    /**
     * 是否已经过期
     * @param expiredTime 过期时间
     * @return
     */
    public static Boolean isOutOfDate(Date expiredTime){
        Long now = Calendar.getInstance().getTimeInMillis();
        Long expiredTimeTime = expiredTime.getTime();
        if (now > expiredTimeTime){
            return true;
        }
        return false;
    }

    /**
     * BigDecimal转换为String
     * @param p
     * @return
     */
    public static String toPlain(BigDecimal p){
        return p.stripTrailingZeros().toPlainString();
    }

    /**
     * 人民币由元向分转换（返回字符串类型）
     * @param p
     * @return
     */
    public static String yuanToFenPlainString(BigDecimal p){
        p = p.multiply(new BigDecimal("100"));
        return CommonUtil.toPlain(p);
    }


    /**
     * 生成10位的时间戳
     * @return
     */
    public static String timestamp10(){
        //Calendar默认生成13位的时间戳
        Long timeInMillis13 = Calendar.getInstance().getTimeInMillis();
        String timeInMillis13Str = timeInMillis13.toString();
        return timeInMillis13Str.substring(0, timeInMillis13Str.length()  - 3);
    }
}

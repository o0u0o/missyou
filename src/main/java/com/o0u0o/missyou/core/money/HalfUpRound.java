package com.o0u0o.missyou.core.money;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ClassName HalfUpRound
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Descripton: 向上取整
 * @Date 2021/3/10 10:01 上午
 * @Version: v0.0.1
 **/
//@Component
public class HalfUpRound implements IMoneyDiscount {

    /**
     * 计算折扣后的价格
     * @param original
     * @param discount
     * @return
     */
    @Override
    public BigDecimal discount(BigDecimal original, BigDecimal discount) {
        //计算折扣价
        BigDecimal actualMoney = original.multiply(discount);
        //HALF_UP四舍五入模式
        BigDecimal finalMoney = actualMoney.setScale(2, RoundingMode.HALF_UP);
        return finalMoney;
    }
}

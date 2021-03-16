package com.o0u0o.missyou.core.money;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * @ClassName HalfEvenRound
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Descripton: 银行家模式
 * @Date 2021/3/10 10:08 上午
 * @Version: v0.0.1
 **/
//@Component
public class HalfEvenRound implements IMoneyDiscount {
    @Override
    public BigDecimal discount(BigDecimal original, BigDecimal discount) {
        //计算折扣价
        BigDecimal actualMoney = original.multiply(discount);
        //HALF_EVEN 银行家模式
        BigDecimal finalMoney = actualMoney.setScale(2, RoundingMode.HALF_EVEN);
        return finalMoney;
    }
}

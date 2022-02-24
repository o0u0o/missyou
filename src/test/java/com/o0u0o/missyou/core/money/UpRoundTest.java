package com.o0u0o.missyou.core.money;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


@Slf4j
class UpRoundTest {

    @Autowired
    IMoneyDiscount iMoneyDiscount;

    @Test
    void discount() {
        BigDecimal original = new BigDecimal("0.03");
        //分账比例
        BigDecimal rate = new BigDecimal("0.65");
        BigDecimal discount = iMoneyDiscount.discount(original, rate);
        log.info("discount={}", discount);
    }
}
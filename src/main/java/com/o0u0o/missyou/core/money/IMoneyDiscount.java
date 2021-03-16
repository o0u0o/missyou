package com.o0u0o.missyou.core.money;

import java.math.BigDecimal;

/**
 * @ClassName IMoneyDiscount
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Descripton: 优惠券检查逻辑
 * @Date2021/3/10 9:58 上午
 * @Version: v0.0.1
 **/
public interface IMoneyDiscount {

    BigDecimal discount(BigDecimal original, BigDecimal discount);

}

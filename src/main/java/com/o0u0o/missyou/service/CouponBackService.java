package com.o0u0o.missyou.service;

import com.o0u0o.missyou.bo.OrderMessageBO;

/**
 * 优惠券归还业务接口
 * @author o0u0o
 * @date 2021/5/20 7:50 下午
 */
public interface CouponBackService {

    public void returnBack(OrderMessageBO bo);
}

package com.o0u0o.missyou.logic;

import com.o0u0o.missyou.bo.SkuOrderBO;
import com.o0u0o.missyou.core.exception.http.ParameterException;
import com.o0u0o.missyou.dto.OrderDTO;
import com.o0u0o.missyou.dto.SkuInfoDTO;
import com.o0u0o.missyou.model.Sku;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderChecker
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Descripton: 订单检查逻辑
 *           作用，内部需要调用CouponChecker
 *           调用关系（外观模式 facade）：
 *           OrderServer -> OrderChecker -> CouponChecker
 * @Date 2021/3/9 4:10 下午
 * @Version: v0.0.1
 **/
//@Service
//@Scope("prototype")
public class OrderChecker {

    private OrderDTO orderDTO;

    private List<Sku> serverSkuList;

    private CouponChecker couponChecker;

    private Integer maxSkuLimit;

    public OrderChecker(OrderDTO orderDTO, List<Sku> serverSkuList,
                        CouponChecker couponChecker, Integer maxSkuLimit){
        this.orderDTO = orderDTO;
        this.serverSkuList = serverSkuList;
        this.couponChecker = couponChecker;
        this.maxSkuLimit = maxSkuLimit;
    }

    public void isOk(){
        //1、校验orderTotalPrice 和 serverTotalPrice
        //2、校验sku是否下架
        //3、所购买的商品是否售罄(再次建议是合理的，在高并发环境下但是可能无效)
        //4、超库存校验
        //5、优惠券校验

        BigDecimal serverTotalPrice = new BigDecimal("0");
        List<SkuOrderBO> skuOrderBOList = new ArrayList<>();

        this.skuNotOnSale(orderDTO.getSkuInfoList().size(), this.serverSkuList.size());

        for (int i = 0; i < this.serverSkuList.size(); i++) {
            Sku sku = this.serverSkuList.get(i);
            SkuInfoDTO skuInfoDTO = this.orderDTO.getSkuInfoList().get(i);
            //检查当前的sku是否售罄
            this.containsSoldOutSku(sku);
            //校验是否出现超卖的情况
            this.beyondSkuStock(sku, skuInfoDTO);
            //检验是否超库存
            this.beyondMaxSkuLimit(skuInfoDTO);

            serverTotalPrice.add(this.calculateSkuOrderPrice(sku, skuInfoDTO));
            skuOrderBOList.add(new SkuOrderBO(sku, skuInfoDTO));
        }

        //校验服务端计算的总价格和客户端计算的总价格是否一致
        this.totalPriceIsOk(orderDTO.getTotalPrice(), serverTotalPrice);

        //校验优惠券
        if (this.couponChecker != null){
            this.couponChecker.isOk();
            this.couponChecker.canBeUsed(skuOrderBOList, serverTotalPrice);
            this.couponChecker.finalTotalPriceIsOk(orderDTO.getFinalTotalPrice(), serverTotalPrice);
        }
    }

    /**
     * 校验服务端计算的总价格和客户端计算的总价格是否一致
     * @param orderTotalPrice 客户端传入的总价格
     * @param serverTotalPrice 服务端计算的总价格
     */
    private void totalPriceIsOk(BigDecimal orderTotalPrice, BigDecimal serverTotalPrice){
        if (orderTotalPrice.compareTo(serverTotalPrice) != 0){
            throw new ParameterException(50005);
        }
    }

    /**
     * 计算某种sku的订单价格
     * @param sku 服务端的sku
     * @param skuInfoDTO 客户端的sku
     * @return
     */
    private BigDecimal calculateSkuOrderPrice(Sku sku, SkuInfoDTO skuInfoDTO){
        if (skuInfoDTO.getCount() <= 0){
            throw new ParameterException(50007);
        }
        return sku.getActualPrice().multiply(new BigDecimal(skuInfoDTO.getCount()));
    }

    /**
     *
     * @param count1 前端传入的sku的数量
     * @param count2 服务端的sku的数量
     */
    private void skuNotOnSale(int count1, int count2){
        if (count1 != count2){
            throw new ParameterException(50002);
        }
    }

    /**
     * 当前的sku是否售罄(此处为初步的与判断，对于高并发环境还需再判断)
     * @param sku 服务端的sku
     */
    private void containsSoldOutSku(Sku sku){
        if (sku.getStock() == 0){
            throw new ParameterException(50001);
        }
    }

    /**
     * 校验是否出现超卖的情况
     * @param sku 服务端的sku
     * @param skuInfoDTO 客户端的sku
     */
    private void beyondSkuStock(Sku sku, SkuInfoDTO skuInfoDTO){
        if (sku.getStock() < skuInfoDTO.getCount()){
            throw new ParameterException(50003);
        }
    }

    /**
     * 检验用户订单是否超出最大限制（配置文件设置的限制）
     * @param skuInfoDTO 客户端传入的订单信息
     */
    private void beyondMaxSkuLimit(SkuInfoDTO skuInfoDTO){
        if (skuInfoDTO.getCount() > this.maxSkuLimit){
            throw new ParameterException(50004);
        }
    }


}

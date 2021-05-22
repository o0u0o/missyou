package com.o0u0o.missyou.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.o0u0o.missyou.common.utils.GenericAndJson;
import com.o0u0o.missyou.core.enumeration.OrderStatus;
import com.o0u0o.missyou.common.utils.CommonUtil;
import com.o0u0o.missyou.dto.OrderAddressDTO;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 订单
 * @author o0u0o
 * @date 2020/12/16 3:03 下午
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "delete_time is null")
@Table(name = "`Order`")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    /** 自增的数字类型的id 性能好 */
    private Long id;

    /** 订单号（两个订单号有助于以后分库分表） */
    private String orderNo;

    /** 当前订单所属用户id */
    private Long userId;

    /** 订单总价格（元素价格） */
    private BigDecimal totalPrice;

    /** 订单有多少sku-总数量 */
    private Long totalCount;

    /** 订单快照图片 */
    private String snapImg;

    /** 订单标题快照 */
    private String snapTitle;

    /** 订单过期时间 */
    private Date expiredTime;

    /** 下单的时间 */
    private Date placedTime;

    /** 当前订单所有要购买的信息 */
    private String snapItems;

    /** 用户收货地址快照 */
    private String snapAddress;

    /** 微信支付使用 */
    private String prepayId;

    /** 这笔订单最终价格（扣除优惠券之后的实际价格） */
    private BigDecimal finalTotalPrice;

    /** 订单状态 */
    private Integer status;

    //充血模式(模型层写业务代码) 贫血模式(模型层不写任何业务代码)
    @JsonIgnore
    public OrderStatus getStatusEnum() {
        return OrderStatus.toType(this.status);
    }

    /**
     * 该订单是否应该被取消
     * @return
     */
    public Boolean needCancel() {
        //订单状态为未支付
        if (!this.getStatusEnum().equals(OrderStatus.UNPAID)) {
            return true;
        }
        //判断是否过期
        boolean isOutOfDate = CommonUtil.isOutOfDate(this.getExpiredTime());
        if (isOutOfDate) {
            return true;
        }
        return false;
    }


    public void setSnapItems(List<OrderSku> orderSkuList) {
        if (orderSkuList.isEmpty()) {
            return;
        }
        this.snapItems = GenericAndJson.objectToJson(orderSkuList);
    }

    public List<OrderSku> getSnapItems() {
        List<OrderSku> list = GenericAndJson.jsonToObject(this.snapItems,
                new TypeReference<List<OrderSku>>() {
                });
        return list;
    }


    public OrderAddressDTO getSnapAddress() {
        if (this.snapAddress == null) {
            return null;
        }
        OrderAddressDTO o = GenericAndJson.jsonToObject(this.snapAddress, new TypeReference<OrderAddressDTO>() {});
        return o;
    }

    public void setSnapAddress(OrderAddressDTO address) {
        this.snapAddress = GenericAndJson.objectToJson(address);
    }

}

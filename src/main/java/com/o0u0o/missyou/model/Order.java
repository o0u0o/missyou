package com.o0u0o.missyou.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.o0u0o.missyou.core.enumeration.OrderStatus;
import com.o0u0o.missyou.common.utils.CommonUtil;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.util.Date;

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
    private Long id;

    /** 订单号 */
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

    private Date expiredTime;

    private Date placedTime;

    /** 当前订单所有要购买的信息 */
    private String snapItems;

    /** 用户收货地址快照 */
    private String snapAddress;

    /** 微信支付使用 */
    private String prepayId;

    /** 这笔订单最终价格（扣除优惠券之后的实际价格） */
    private BigDecimal finalTotalPrice;

    private Integer status;
//
//    //充血模式 贫血模式
//    @JsonIgnore
//    public OrderStatus getStatusEnum() {
//        return OrderStatus.toType(this.status);
//    }
//
//    //
//    public Boolean needCancel() {
//        if (!this.getStatusEnum().equals(OrderStatus.UNPAID)) {
//            return true;
//        }
//        boolean isOutOfDate = CommonUtil.isOutOfDate(this.getExpiredTime());
//        if (isOutOfDate) {
//            return true;
//        }
//        return false;
//    }
//
//    //
//    public void setSnapItems(List<OrderSku> orderSkuList) {
//        if (orderSkuList.isEmpty()) {
//            return;
//        }
//        this.snapItems = GenericAndJson.objectToJson(orderSkuList);
//    }
//
//    public List<OrderSku> getSnapItems() {
//        List<OrderSku> list = GenericAndJson.jsonToObject(this.snapItems,
//                new TypeReference<List<OrderSku>>() {
//                });
//        return list;
//    }
//
//
//    public OrderAddressDTO getSnapAddress() {
//        if (this.snapAddress == null) {
//            return null;
//        }
//        OrderAddressDTO o = GenericAndJson.jsonToObject(this.snapAddress,
//                new TypeReference<OrderAddressDTO>() {
//                });
//        return o;
//    }
//
//    public void setSnapAddress(OrderAddressDTO address) {
//        this.snapAddress = GenericAndJson.objectToJson(address);
//    }

}

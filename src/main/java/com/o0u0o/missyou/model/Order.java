//package com.o0u0o.missyou.model;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.o0u0o.missyou.core.enumeration.OrderStatus;
//import com.o0u0o.missyou.common.utils.CommonUtil;
//import lombok.*;
//import org.hibernate.annotations.Where;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import static javax.persistence.GenerationType.IDENTITY;
//
///**
// * 订单
// * @author mac
// * @date 2020/12/16 3:03 下午
// */
//@Entity
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Where(clause = "delete_time is null")
//@Table(name = "`Order`")
//public class Order extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    private Long id;
//    private String orderNo;
//    private Long userId;
//    private BigDecimal totalPrice;
//    private Long totalCount;
//    private String snapImg;
//    private String snapTitle;
//    private Date expiredTime;
//    private Date placedTime;
//
//    private String snapItems;
//
//    private String snapAddress;
//
//    private String prepayId;
//    private BigDecimal finalTotalPrice;
//    private Integer status;
//
//    //充血模式 贫血模式
//
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
//
//}

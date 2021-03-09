package com.o0u0o.missyou.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName OrderAddressDTO
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/3/5 11:40 上午
 * @Descripton: 订单地址数据传输对象
 * @Version: v0.0.1
 **/
@Getter
@Setter
public class OrderAddressDTO {
    private String userName;
    private String province;
    private String city;
    private String county;
    private String mobile;

    /** 国家码 */
    private String nationalCode;

    /** 邮政编码 */
    private String postalCode;

    /** 详细号码 */
    private String detail;
}

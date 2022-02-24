package com.o0u0o.missyou.core;

import com.o0u0o.missyou.core.exception.success.CreateSuccess;
import lombok.Data;

/**
 * @ClassName UnifyResponse
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/9 上午12:15
 * @Descripton: 统一响应
 * @Version: v0.0.1
 **/
@Data
public class UnifyResponse {

    /** 响应码 */
    private Integer code;

    /** 消息 */
    private String message;

    /** 请求地址 */
    private String request;

    public UnifyResponse() {
    }

    public UnifyResponse(Integer code, String message, String request) {
        this.code = code;
        this.message = message;
        this.request = request;
    }



    public static void createSuccess(int code){
        throw new CreateSuccess(0);
    }

}

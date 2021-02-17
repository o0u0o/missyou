package com.o0u0o.missyou.core.exception;

import lombok.Data;

/**
 * @ClassName HttpException
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/8 上午12:13
 * @Descripton: 自定义的HttpException 需继承 RuntimeException
 * @Version: v0.0.1
 **/
@Data
public class HttpException extends RuntimeException {

    /** 错误码 */
    protected Integer code;

    /** http 的状态码*/
    protected Integer httpStatusCode = 500;



}

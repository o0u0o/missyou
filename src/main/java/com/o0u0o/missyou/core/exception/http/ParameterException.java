package com.o0u0o.missyou.core.exception.http;

import com.o0u0o.missyou.core.exception.HttpException;

/**
 * @ClassName ParameterException
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/10 11:36 上午
 * @Descripton: 参数异常
 * @Version: v0.0.1
 **/
public class ParameterException extends HttpException {
    public ParameterException(int code) {
        this.code = code;
        this.httpStatusCode = 400;
    }
}

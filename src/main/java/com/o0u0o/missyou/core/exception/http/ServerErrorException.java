package com.o0u0o.missyou.core.exception.http;

import com.o0u0o.missyou.core.exception.HttpException;

/**
 * @ClassName ServerErrorException
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/8/26 下午11:20
 * @Descripton: 服务器错误异常
 * @Version: v0.0.1
 **/
public class ServerErrorException extends HttpException {

    public ServerErrorException(int code) {
        this.code = code;
        this.httpStatusCode = 500;
    }
}

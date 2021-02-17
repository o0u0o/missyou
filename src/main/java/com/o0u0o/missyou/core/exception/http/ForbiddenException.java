package com.o0u0o.missyou.core.exception.http;

import com.o0u0o.missyou.core.exception.HttpException;
import org.springframework.http.HttpStatus;

/**
 * @ClassName ForbiddenException
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/8 上午12:19
 * @Descripton: 禁止访问异常
 * @Version: v0.0.1
 **/
public class ForbiddenException extends HttpException {

    public ForbiddenException(int code) {
        this.code = code;
        this.httpStatusCode = HttpStatus.FORBIDDEN.value();
    }
}

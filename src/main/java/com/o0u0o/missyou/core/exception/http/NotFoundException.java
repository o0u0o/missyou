package com.o0u0o.missyou.core.exception.http;


import com.o0u0o.missyou.core.exception.HttpException;
import org.springframework.http.HttpStatus;

/**
 * @ClassName NotFoundException
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/8 上午12:15
 * @Descripton: 自定义找不到资源异常 继承 HttpException
 * @Version: v0.0.1
 **/
public class NotFoundException extends HttpException {

    public NotFoundException(int code){
        this.code = code;
        this.httpStatusCode = HttpStatus.NOT_FOUND.value();

    }
}

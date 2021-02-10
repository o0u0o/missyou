package com.o0u0o.missyou.core.http;

/**
 * @ClassName UnAuthenticated
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/10 4:58 下午
 * @Descripton: 未登录授权
 * @Version: v0.0.1
 **/
public class UnAuthenticatedException extends HttpException {
    public UnAuthenticatedException(int code) {
        this.code = code;
        this.httpStatusCode = 401;
    }
}

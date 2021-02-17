package com.o0u0o.missyou.core.exception.success;

import com.o0u0o.missyou.core.exception.HttpException;

/**
 * @ClassName CreateSuccess
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/17 4:11 下午
 * @Descripton: 删除成功
 * @Version: v0.0.1
 **/
public class DeleteSuccess extends HttpException {
    public DeleteSuccess(int code){
        this.httpStatusCode = 200;
        this.code = code;
    }
}

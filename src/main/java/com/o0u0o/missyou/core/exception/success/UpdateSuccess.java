package com.o0u0o.missyou.core.exception.success;

import com.o0u0o.missyou.core.exception.HttpException;

/**
 * @ClassName UpdateSuccess
 * @Author o0u0o
 * @UpdateUser o0u0o
 * @Date 2021/2/17 4:15 下午
 * @Descripton: 更新资源成功
 *  1、查询成功-200
 *  2、创建资源成功-201
 *  3、删除资源成功-200(restful为204，但默认不会返回结果)
 *  4、更新资源成功-200
 * @Version: v0.0.1
 **/
public class UpdateSuccess extends HttpException {
    public UpdateSuccess(int code){
        this.httpStatusCode = 200;
        this.code = code;
    }
}

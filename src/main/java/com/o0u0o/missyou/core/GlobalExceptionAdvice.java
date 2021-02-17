package com.o0u0o.missyou.core;

import com.o0u0o.missyou.core.configuration.ExceptionCodeConfiguration;
import com.o0u0o.missyou.core.exception.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @ClassName GlobalExceptionAdvice
 * @Author aiuiot
 * @UpdateUser aiuiot
 * @Date 2020/7/3 下午10:43
 * @Descripton: 全局异常处理
 *
 *  定义一个全局异常处理累需要以下使用注解:
 *      1、@ControllerAdvice 标记在异常处理类上 用户处理异常
 *      2、具体方法上需要添加注解@ExceptionHandler
 *
 * @Version: v0.0.1
 **/
@ControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    private ExceptionCodeConfiguration exceptionCodeConfiguration;

    /**
     * 异常处理器
     * 1、使用注解 @ExceptionHandler 标注为异常处理器
     * 2、value = Exception.class 捕获异常为 Exception 异常
     * 3、对于未知异常不应该详细返回给前端(为避免泄漏代码结构且对前端没有意义) 可提示"服务器未知错误"
     * 该方法用户处理未知异常
     */

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest request, Exception e){
        String uri = request.getRequestURI();
        String method = request.getMethod();
        System.out.println("堆栈信息:" + e);
        return new UnifyResponse(9999, "服务器未知异常", method+" "+uri);
    }


    /**
     * 用户处理HttpException 的异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<UnifyResponse> httpExceptionHandler(HttpServletRequest request, HttpException e){
        String uri = request.getRequestURI();
        String method = request.getMethod();
        UnifyResponse message = new UnifyResponse(e.getCode(), exceptionCodeConfiguration.getMessage(e.getCode()), method + " " + uri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());

        return new ResponseEntity<>(message, headers, httpStatus);
    }

    /**
     *
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponse beanValidationHandler(HttpServletRequest request, MethodArgumentNotValidException e){
        String uri = request.getRequestURI();
        String method = request.getMethod();

        //获取所有的错误信息
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        //组合成一个字符串
        String messages = formatAllErrorMessages(errors);
        return new UnifyResponse(10001, messages, method + " " + uri);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public UnifyResponse constraintExceptionHandler(HttpServletRequest request, ConstraintViolationException e){
        String uri = request.getRequestURI();
        String method = request.getMethod();

        //获取所有的错误信息
//        e.getMessage()
//        for (ConstraintViolation<?> error : e.getConstraintViolations()) {
//            error.getMessageTemplate();
//        }

        return new UnifyResponse(10001, e.getMessage(),method + " " + uri);
    }



    /*************** 私有方法 ***************/
    private String formatAllErrorMessages(List<ObjectError> errors){
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error ->
                errorMsg.append(error.getDefaultMessage()).append(";")
        );
        return errorMsg.toString();
    }
}

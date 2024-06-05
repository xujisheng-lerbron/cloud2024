package com.xujisheng.cloud.exception;

import com.xujisheng.cloud.Constant.ResultData;
import com.xujisheng.cloud.Constant.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  统一全局异常处理，需要使用时开启注解
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e){

        System.out.println("come in GlobalExceptionHandler!");
        log.error("全局异常信息：{}", e.getMessage(), e);
        return  ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
    }
}

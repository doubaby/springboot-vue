package com.lgq.springboot.exception;

import com.lgq.springboot.common.Result;
import com.lgq.springboot.common.Status;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;

/**
 * @author LGQ
 * @createTime 2022/8/6 20:33
 * @description
 */

@ControllerAdvice
public class ServiceExceptionHandler {

    /**
     * @param se 业务异常
     * @return
     * @ExceptionHandler相当于controller的@RequestMapping 如果抛出的的是ServiceException，则调用该方法
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException se) {
        return Result.error(se.getCode(), se.getMessage());
    }
}

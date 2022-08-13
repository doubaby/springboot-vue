package com.lgq.springboot.exception;

import lombok.Getter;

/**
 * @author LGQ
 * @createTime 2022/8/6 20:33
 * @description
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }
}

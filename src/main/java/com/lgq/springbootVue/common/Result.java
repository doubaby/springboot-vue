package com.lgq.springbootVue.common;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author LGQ
 * @createTime 2022/8/6 20:15
 * @description 接口同意返回的包装类
 */
@Data
@NoAutoStart
@AllArgsConstructor
public class Result {

    private String code;
    private String msg;
    private Object data;

    public static Result success() {
        return new Result(Status.CODE_200, "", null);
    }

    public static Result success(Object data) {
        return new Result(Status.CODE_200, "", data);
    }

    public static Result error(String code, String msg) {
        return new Result(Status.CODE_400, msg, null);
    }

    public static Result error() {
        return new Result(Status.CODE_500, "系统错误", null);
    }

}

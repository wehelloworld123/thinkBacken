package com.myIsoland.common.base;

import com.myIsoland.enums.CodeEnum;
import groovy.transform.ToString;
import lombok.Data;

/**
 * 通用异常
 *
 */
@ToString
@Data
public class ThinkException extends RuntimeException {
    private static final long serialVersionUID = 111820440373615072L;

    /**
     * 异常代码
     */
    private int code;
    /**
     * 异常消息
     */
    private String message;

    public ThinkException(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    public ThinkException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ThinkException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }


}

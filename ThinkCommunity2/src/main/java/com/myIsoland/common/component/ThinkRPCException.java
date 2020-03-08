package com.myIsoland.common.component;

import com.myIsoland.common.base.ThinkException;
import com.myIsoland.enums.CodeEnum;

public class ThinkRPCException extends ThinkException {
    public ThinkRPCException(CodeEnum codeEnum) {
        super(codeEnum);
    }

    public ThinkRPCException(int code, String message) {
        super(code, message);
    }

    public ThinkRPCException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }
}

package com.myIsoland.common.component;

import com.myIsoland.common.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;

public class SingleParamException extends ServletRequestBindingException {
    public SingleParamException(String msg) {
        super(StringUtils.isEmpty(msg)?"999":msg);
    }

    public SingleParamException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

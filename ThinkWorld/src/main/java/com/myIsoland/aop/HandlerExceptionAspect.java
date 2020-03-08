package com.myIsoland.aop;


import com.myIsoland.common.component.SingleParamException;
import com.myIsoland.common.component.ThinkRPCException;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.StringUtils;
import com.myIsoland.enums.CodeEnum;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * 控制层异常切面
 */
@Slf4j
@ControllerAdvice
public class HandlerExceptionAspect {

    private final Logger log = LoggerFactory.getLogger(HandlerExceptionAspect.class);
    /**
     * 统一处理JSON响应结果的错误结果 例如：{"code":201,message:"入参异常",data:null}
     *
     * @param cause   {@link Throwable} 异常
     * @param request {@link HttpServletRequest} 请求
     * @return {@link AjaxResult} 响应结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object process(Throwable cause, HttpServletRequest request, HttpServletResponse response) {
        this.setHeader(request, response);
        log.error("error:{}", cause);
        if (cause instanceof ThinkRPCException) {
            return  AjaxResult.error(((ThinkRPCException) cause).getCode(),((ThinkRPCException) cause).getMessage(), cause.getMessage());

        } else if (cause instanceof MethodArgumentNotValidException) {
            String errorMessage = this.methodArgumentMessage((MethodArgumentNotValidException) cause);
            return AjaxResult.error(CodeEnum.INCORRECT_REQ_PARAM.getCode(),CodeEnum.INCORRECT_REQ_PARAM.getMessage(), errorMessage);
        } else if (cause instanceof SingleParamException) {
            String errorMessage = CodeEnum.getMessageByCode(Integer.valueOf(cause.getMessage()));
            return AjaxResult.error(CodeEnum.INCORRECT_REQ_PARAM.getCode(),CodeEnum.INCORRECT_REQ_PARAM.getMessage(),errorMessage);
            //等等异常分类
        } else if( cause instanceof SQLException){
            return AjaxResult.error(CodeEnum.SQL_EXCEPTION_CODE.getCode(),CodeEnum.SQL_EXCEPTION_CODE.getMessage(), cause.getMessage());
        }else {
            return AjaxResult.error(CodeEnum.UNKOWN_ERROR.getCode(), CodeEnum.UNKOWN_ERROR.getMessage(),null);
        }
    }

    /**
     * 参数异常消息获取
     *
     * @param cause 参数异常
     * @return 异常原因
     */
    private String methodArgumentMessage(MethodArgumentNotValidException cause) {
        MethodArgumentNotValidException validException = cause;
        BindingResult bindingResult = validException.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder builder = new StringBuilder();
        for (ObjectError objectError : allErrors) {
            if (StringUtils.isNotBlank(objectError.getDefaultMessage())) {
                if (StringUtils.isNotBlank(builder)) {
                    builder.append(";");
                }
                builder.append(objectError.getDefaultMessage());
            }

        }
        if (StringUtils.isBlank(builder)) {
            builder.append(CodeEnum.INCORRECT_REQ_PARAM.getMessage());
        }
        return builder.toString();
    }

    /**
     * 响应头部跨域设置
     *
     * @param request
     * @param response
     */
    private void setHeader(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");

    }
}

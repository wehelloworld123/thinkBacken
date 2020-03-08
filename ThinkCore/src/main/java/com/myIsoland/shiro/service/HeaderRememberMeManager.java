package com.myIsoland.shiro.service;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.AbstractRememberMeManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.subject.WebSubjectContext;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class HeaderRememberMeManager extends AbstractRememberMeManager {

    private final transient Logger log = LoggerFactory.getLogger(HeaderRememberMeManager.class);
    //header中固定使用的key
    public static final String DEFAULT_REMEMBER_ME_HEADER_NAME = "remember";


    @Override
    protected void rememberSerializedIdentity(Subject subject, byte[] serialized) {
        if(!WebUtils.isHttp(subject)){
            if(log.isDebugEnabled()){
                String msg = "Subject argument is not an HTTP-aware instance.  This is required to obtain a servlet request and response in order to set the rememberMe cookie. Returning immediately and ignoring rememberMe operation.";

                log.debug(msg);
            }else{
                HttpServletResponse response = WebUtils.getHttpResponse(subject);
                String base64 = Base64.encodeToString(serialized);
                //设置rememberMe信息到response header中
                response.setHeader(DEFAULT_REMEMBER_ME_HEADER_NAME,base64);
            }
        }
    }

    private boolean isIdentityRemoved(WebSubjectContext subjectContext) {
        ServletRequest request = subjectContext.resolveServletRequest();
        if (request == null) {
            return false;
        } else {
            Boolean removed = (Boolean) request.getAttribute(ShiroHttpServletRequest.IDENTITY_REMOVED_KEY);
            return removed != null && removed;
        }
    }

    @Override
    protected byte[] getRememberedSerializedIdentity(SubjectContext subjectContext) {
        if (!WebUtils.isHttp(subjectContext)) {
            if (log.isDebugEnabled()) {
                String msg = "SubjectContext argument is not an HTTP-aware instance.  This is required to obtain a servlet request and response in order to retrieve the rememberMe cookie. Returning immediately and ignoring rememberMe operation.";
                log.debug(msg);
            }

            return null;
        } else {
            WebSubjectContext wsc = (WebSubjectContext) subjectContext;
            if (this.isIdentityRemoved(wsc)) {
                return null;
            } else {
                HttpServletRequest request = WebUtils.getHttpRequest(wsc);
                // 在request header 中获取 rememberMe信息
                String base64 = request.getHeader(DEFAULT_REMEMBER_ME_HEADER_NAME);
                if ("deleteMe".equals(base64)) {
                    return null;
                } else if (base64 != null) {
                    base64 = this.ensurePadding(base64);
                    if (log.isTraceEnabled()) {
                        log.trace("Acquired Base64 encoded identity [" + base64 + "]");
                    }

                    byte[] decoded = Base64.decode(base64);
                    if (log.isTraceEnabled()) {
                        log.trace("Base64 decoded byte array length: " + (decoded != null ? decoded.length : 0) + " bytes.");
                    }

                    return decoded;
                } else {
                    return null;
                }
            }
        }
    }

    private String ensurePadding(String base64) {
        int length = base64.length();
        if (length % 4 != 0) {
            StringBuilder sb = new StringBuilder(base64);

            for (int i = 0; i < length % 4; ++i) {
                sb.append('=');
            }

            base64 = sb.toString();
        }

        return base64;
    }

    protected void forgetIdentity(Subject subject) {
        if (WebUtils.isHttp(subject)) {
            HttpServletRequest request = WebUtils.getHttpRequest(subject);
            HttpServletResponse response = WebUtils.getHttpResponse(subject);
            this.forgetIdentity(request, response);
        }

    }

    public void forgetIdentity(SubjectContext subjectContext) {
        if (WebUtils.isHttp(subjectContext)) {
            HttpServletRequest request = WebUtils.getHttpRequest(subjectContext);
            HttpServletResponse response = WebUtils.getHttpResponse(subjectContext);
            this.forgetIdentity(request, response);
        }
    }

    private void forgetIdentity(HttpServletRequest request, HttpServletResponse response) {
        //设置删除标示
        response.setHeader(DEFAULT_REMEMBER_ME_HEADER_NAME, "deleteMe");
    }


}

package com.myIsoland.shiro.service;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
@Service
public class MyUserFilter extends UserFilter {
    // isAccessAllowed return false 执行
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 这里也可以不用保存 保存当前request 可在登陆后重新请求当前 request
        this.saveRequest(request);
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.getWriter().write("{\"code\":-1,\"message\":\"no.login\"}");
        return false;
    }

}

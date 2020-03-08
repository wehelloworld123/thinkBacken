package com.myIsoland.shiro.service;

import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authz.*;
import org.apache.shiro.web.filter.session.NoSessionCreationFilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.util.LinkedHashMap;
import java.util.Map;

public enum MyDefaultFilter {
    anon(AnonymousFilter.class),
    authc(FormAuthenticationFilter.class),
    authcBasic(BasicHttpAuthenticationFilter.class),
    logout(LogoutFilter.class),
    noSessionCreation(NoSessionCreationFilter.class),
    perms(PermissionsAuthorizationFilter.class),
    port(PortFilter.class),
    rest(HttpMethodPermissionFilter.class),
    roles(RolesAuthorizationFilter.class),
    ssl(SslFilter.class),
    user(MyUserFilter.class);

    private final Class<? extends javax.servlet.Filter> filterClass;

    private MyDefaultFilter(Class<? extends javax.servlet.Filter> filterClass) {
        this.filterClass = filterClass;
    }

    public javax.servlet.Filter newInstance() {
        return (javax.servlet.Filter) ClassUtils.newInstance(this.filterClass);
    }

    public Class<? extends javax.servlet.Filter> getFilterClass() {
        return this.filterClass;
    }

    public static Map<String, javax.servlet.Filter> createInstanceMap(FilterConfig config) {
        Map<String, javax.servlet.Filter> filters = new LinkedHashMap(values().length);
        MyDefaultFilter[] var2 = values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            MyDefaultFilter defaultFilter = var2[var4];
            javax.servlet.Filter filter = defaultFilter.newInstance();
            if (config != null) {
                try {
                    filter.init(config);
                } catch (ServletException var9) {
                    String msg = "Unable to correctly init default filter instance of type " + filter.getClass().getName();
                    throw new IllegalStateException(msg, var9);
                }
            }

            filters.put(defaultFilter.name(), filter);
        }

        return filters;
    }
}

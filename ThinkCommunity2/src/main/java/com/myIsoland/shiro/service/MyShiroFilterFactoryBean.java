package com.myIsoland.shiro.service;

import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;

import javax.servlet.Filter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {
    private static final transient Logger log = LoggerFactory.getLogger(MyShiroFilterFactoryBean.class);
    private SecurityManager securityManager;
    private Map<String, Filter> filters = new LinkedHashMap();
    private Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
    private String loginUrl;
    private String successUrl;
    private String unauthorizedUrl;
    private AbstractShiroFilter instance;

    public MyShiroFilterFactoryBean() {
    }

    public SecurityManager getSecurityManager() {
        return this.securityManager;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    public String getLoginUrl() {
        return this.loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return this.successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getUnauthorizedUrl() {
        return this.unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    public Map<String, Filter> getFilters() {
        return this.filters;
    }

    public void setFilters(Map<String, Filter> filters) {
        this.filters = filters;
    }

    public Map<String, String> getFilterChainDefinitionMap() {
        return this.filterChainDefinitionMap;
    }

    public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
        this.filterChainDefinitionMap = filterChainDefinitionMap;
    }

    public void setFilterChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection("urls");
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection("");
        }

        this.setFilterChainDefinitionMap(section);
    }

    public Object getObject() throws Exception {
        if (this.instance == null) {
            this.instance = this.createInstance();
        }

        return this.instance;
    }

    public Class getObjectType() {
        return MyShiroFilterFactoryBean.SpringShiroFilter.class;
    }

    public boolean isSingleton() {
        return true;
    }

    protected FilterChainManager createFilterChainManager() {
        MyDefaultFilterChainManager manager = new MyDefaultFilterChainManager();
        Map<String, Filter> defaultFilters = manager.getFilters();
        Iterator var3 = defaultFilters.values().iterator();

        while(var3.hasNext()) {
            Filter filter = (Filter)var3.next();
            this.applyGlobalPropertiesIfNecessary(filter);
        }

        Map<String, Filter> filters = this.getFilters();
        String name;
        Filter filter;
        if (!CollectionUtils.isEmpty(filters)) {
            for(Iterator var10 = filters.entrySet().iterator(); var10.hasNext(); manager.addFilter(name, filter, false)) {
                Map.Entry<String, Filter> entry = (Map.Entry)var10.next();
                name = (String)entry.getKey();
                filter = (Filter)entry.getValue();
                this.applyGlobalPropertiesIfNecessary(filter);
                if (filter instanceof Nameable) {
                    ((Nameable)filter).setName(name);
                }
            }
        }

        Map<String, String> chains = this.getFilterChainDefinitionMap();
        if (!CollectionUtils.isEmpty(chains)) {
            Iterator var12 = chains.entrySet().iterator();

            while(var12.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry)var12.next();
                String url = (String)entry.getKey();
                String chainDefinition = (String)entry.getValue();
                manager.createChain(url, chainDefinition);
            }
        }

        return manager;
    }

    protected AbstractShiroFilter createInstance() throws Exception {
        log.debug("Creating Shiro Filter instance.");
        SecurityManager securityManager = this.getSecurityManager();
        String msg;
        if (securityManager == null) {
            msg = "SecurityManager property must be set.";
            throw new BeanInitializationException(msg);
        } else if (!(securityManager instanceof WebSecurityManager)) {
            msg = "The security manager does not implement the WebSecurityManager interface.";
            throw new BeanInitializationException(msg);
        } else {
            FilterChainManager manager = this.createFilterChainManager();
            PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
            chainResolver.setFilterChainManager(manager);
            return new MyShiroFilterFactoryBean.SpringShiroFilter((WebSecurityManager)securityManager, chainResolver);
        }
    }

    private void applyLoginUrlIfNecessary(Filter filter) {
        String loginUrl = this.getLoginUrl();
        if (StringUtils.hasText(loginUrl) && filter instanceof AccessControlFilter) {
            AccessControlFilter acFilter = (AccessControlFilter)filter;
            String existingLoginUrl = acFilter.getLoginUrl();
            if ("/login.jsp".equals(existingLoginUrl)) {
                acFilter.setLoginUrl(loginUrl);
            }
        }

    }

    private void applySuccessUrlIfNecessary(Filter filter) {
        String successUrl = this.getSuccessUrl();
        if (StringUtils.hasText(successUrl) && filter instanceof AuthenticationFilter) {
            AuthenticationFilter authcFilter = (AuthenticationFilter)filter;
            String existingSuccessUrl = authcFilter.getSuccessUrl();
            if ("/".equals(existingSuccessUrl)) {
                authcFilter.setSuccessUrl(successUrl);
            }
        }

    }

    private void applyUnauthorizedUrlIfNecessary(Filter filter) {
        String unauthorizedUrl = this.getUnauthorizedUrl();
        if (StringUtils.hasText(unauthorizedUrl) && filter instanceof AuthorizationFilter) {
            AuthorizationFilter authzFilter = (AuthorizationFilter)filter;
            String existingUnauthorizedUrl = authzFilter.getUnauthorizedUrl();
            if (existingUnauthorizedUrl == null) {
                authzFilter.setUnauthorizedUrl(unauthorizedUrl);
            }
        }

    }

    private void applyGlobalPropertiesIfNecessary(Filter filter) {
        this.applyLoginUrlIfNecessary(filter);
        this.applySuccessUrlIfNecessary(filter);
        this.applyUnauthorizedUrlIfNecessary(filter);
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Filter) {
            log.debug("Found filter chain candidate filter '{}'", beanName);
            Filter filter = (Filter)bean;
            this.applyGlobalPropertiesIfNecessary(filter);
            this.getFilters().put(beanName, filter);
        } else {
            log.trace("Ignoring non-Filter bean '{}'", beanName);
        }

        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private static final class SpringShiroFilter extends AbstractShiroFilter {
        protected SpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
            if (webSecurityManager == null) {
                throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
            } else {
                this.setSecurityManager(webSecurityManager);
                if (resolver != null) {
                    this.setFilterChainResolver(resolver);
                }

            }
        }
    }
}

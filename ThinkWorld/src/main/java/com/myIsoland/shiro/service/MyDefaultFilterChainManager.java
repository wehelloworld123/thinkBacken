package com.myIsoland.shiro.service;

import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.PathConfigProcessor;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.apache.shiro.web.filter.mgt.SimpleNamedFilterList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MyDefaultFilterChainManager extends DefaultFilterChainManager {
    private static final transient Logger log = LoggerFactory.getLogger(MyDefaultFilterChainManager.class);


    public MyDefaultFilterChainManager() {
        this.addDefaultFilters(false);
    }

    public MyDefaultFilterChainManager(FilterConfig filterConfig) {
        this.setFilterConfig(filterConfig);
        this.addDefaultFilters(true);
    }





    public void addFilter(String name, Filter filter) {
        this.addFilter(name, filter, false);
    }

    public void addFilter(String name, Filter filter, boolean init) {
        this.addFilter(name, filter, init, true);
    }

    public void createChain(String chainName, String chainDefinition) {
        if (!StringUtils.hasText(chainName)) {
            throw new NullPointerException("chainName cannot be null or empty.");
        } else if (!StringUtils.hasText(chainDefinition)) {
            throw new NullPointerException("chainDefinition cannot be null or empty.");
        } else {
            if (log.isDebugEnabled()) {
                log.debug("Creating chain [" + chainName + "] from String definition [" + chainDefinition + "]");
            }

            String[] filterTokens = this.splitChainDefinition(chainDefinition);
            String[] var4 = filterTokens;
            int var5 = filterTokens.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String token = var4[var6];
                String[] nameConfigPair = this.toNameConfigPair(token);
                this.addToChain(chainName, nameConfigPair[0], nameConfigPair[1]);
            }

        }
    }

    protected String[] splitChainDefinition(String chainDefinition) {
        return StringUtils.split(chainDefinition, ',', '[', ']', true, true);
    }

    protected String[] toNameConfigPair(String token) throws ConfigurationException {
        String name;
        try {
            String[] pair = token.split("\\[", 2);
            name = StringUtils.clean(pair[0]);
            if (name == null) {
                throw new IllegalArgumentException("Filter name not found for filter chain definition token: " + token);
            } else {
                String config = null;
                if (pair.length == 2) {
                    config = StringUtils.clean(pair[1]);
                    config = config.substring(0, config.length() - 1);
                    config = StringUtils.clean(config);
                    if (config != null && config.startsWith("\"") && config.endsWith("\"")) {
                        String stripped = config.substring(1, config.length() - 1);
                        stripped = StringUtils.clean(stripped);
                        if (stripped != null && stripped.indexOf(34) == -1) {
                            config = stripped;
                        }
                    }
                }

                return new String[]{name, config};
            }
        } catch (Exception var6) {
            name = "Unable to parse filter chain definition token: " + token;
            throw new ConfigurationException(name, var6);
        }
    }



    public void addToChain(String chainName, String filterName) {
        this.addToChain(chainName, filterName, (String)null);
    }

    public void addToChain(String chainName, String filterName, String chainSpecificFilterConfig) {
        if (!StringUtils.hasText(chainName)) {
            throw new IllegalArgumentException("chainName cannot be null or empty.");
        } else {
            Filter filter = this.getFilter(filterName);
            if (filter == null) {
                throw new IllegalArgumentException("There is no filter with name '" + filterName + "' to apply to chain [" + chainName + "] in the pool of available Filters.  Ensure a " + "filter with that name/path has first been registered with the addFilter method(s).");
            } else {
                this.applyChainConfig(chainName, filter, chainSpecificFilterConfig);
                NamedFilterList chain = this.ensureChain(chainName);
                chain.add(filter);
            }
        }
    }

    protected void applyChainConfig(String chainName, Filter filter, String chainSpecificFilterConfig) {
        if (log.isDebugEnabled()) {
            log.debug("Attempting to apply path [" + chainName + "] to filter [" + filter + "] " + "with config [" + chainSpecificFilterConfig + "]");
        }

        if (filter instanceof PathConfigProcessor) {
            ((PathConfigProcessor)filter).processPathConfig(chainName, chainSpecificFilterConfig);
        } else if (StringUtils.hasText(chainSpecificFilterConfig)) {
            String msg = "chainSpecificFilterConfig was specified, but the underlying Filter instance is not an 'instanceof' " + PathConfigProcessor.class.getName() + ".  This is required if the filter is to accept " + "chain-specific configuration.";
            throw new ConfigurationException(msg);
        }

    }



    public FilterChain proxy(FilterChain original, String chainName) {
        NamedFilterList configured = this.getChain(chainName);
        if (configured == null) {
            String msg = "There is no configured chain under the name/key [" + chainName + "].";
            throw new IllegalArgumentException(msg);
        } else {
            return configured.proxy(original);
        }
    }

    protected void initFilter(Filter filter) {
        FilterConfig filterConfig = this.getFilterConfig();
        if (filterConfig == null) {
            throw new IllegalStateException("FilterConfig attribute has not been set.  This must occur before filter initialization can occur.");
        } else {
            try {
                filter.init(filterConfig);
            } catch (ServletException var4) {
                throw new ConfigurationException(var4);
            }
        }
    }

    protected void addDefaultFilters(boolean init) {

        //使用我们创建的 DefaultFilter
        MyDefaultFilter[] var2 = MyDefaultFilter.values();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            MyDefaultFilter defaultFilter = var2[var4];
            super.addFilter(defaultFilter.name(), defaultFilter.newInstance(), init, false);
        }

    }
}

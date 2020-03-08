package com.myIsoland.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.myIsoland.common.component.KickoutSessionControlFilter;
import com.myIsoland.common.component.RedisObjectSerializer;
import com.myIsoland.common.component.StringRedisSerializer;
import com.myIsoland.common.exception.GlobalExceptionResolver;
import com.myIsoland.shiro.service.DefaultHeaderSessionManager;
import com.myIsoland.shiro.service.HeaderRememberMeManager;
import com.myIsoland.shiro.service.MyShiroFilterFactoryBean;
import com.myIsoland.shiro.service.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import redis.clients.jedis.JedisPool;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 权限配置文件
 * @ClassName: ShiroConfiguration
 * @author fuce
 * @date 2018年8月25日
 *
 */
@Configuration
public class ShiroConfig {

	/**
	 * 这是shiro的大管家，相当于mybatis里的SqlSessionFactoryBean
	 * @param securityManager
	 * @return
	 */
    @Bean(name = "shiroFilter")
    public MyShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        // 使用自行创建的 FactoryBean
        MyShiroFilterFactoryBean shiroFilterFactoryBean = new MyShiroFilterFactoryBean();
		//登录
		shiroFilterFactoryBean.setLoginUrl("/login");
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        shiroFilterFactoryBean.setFilterChainDefinitions("/api/v1/login = anon\n" +
                "/ = anon\n" +
                "/api/v1/website/article/** = anon\n" +
                "/api/v1/** = cors,user\n");

        //页面权限控制
        shiroFilterFactoryBean.setFilterChainDefinitionMap(ShiroFilterMapFactory.shiroFilterMap());
		shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

//	@Bean
//	public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
////		//登录
////		shiroFilterFactoryBean.setLoginUrl("/login");
//
//		//配置shiro默认登陆界面地址，前后端分离中登陆界面跳转应有前端路由控制
//		shiroFilterFactoryBean.setLoginUrl("/Corporation/unauth");
////		//首页
////		shiroFilterFactoryBean.setSuccessUrl("/index");
////		//错误页面，认证不通过跳转
////		shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
//
//		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
//		//限制同一帐号同时在线的个数。
//		filtersMap.put("kickout", kickoutSessionControlFilter());
//		shiroFilterFactoryBean.setFilters(filtersMap);
//		//页面权限控制
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(ShiroFilterMapFactory.shiroFilterMap());
//
//		shiroFilterFactoryBean.setSecurityManager(securityManager);
//		return shiroFilterFactoryBean;
//	}

    /**
     * web应用管理配置
     * @param shiroRealm
     * @param cacheManager
     * @param manager
     * @return
     */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm(hashedCredentialsMatcher()));
		securityManager.setCacheManager(redisCacheManager());
		securityManager.setRememberMeManager(rememberMeManager());//记住Cookie
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}
//	/**
//	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
//	 *
//	 * @return
//	 */
//	@Bean
//	public MyShiroRealm myShiroRealm() {
//		MyShiroRealm myShiroRealm = new MyShiroRealm();
//		return myShiroRealm;
//	}
//	/**
//	 * cacheManager 缓存 redis实现
//	 * 使用的是shiro-redis开源插件
//	 *
//	 * @return
//	 */
	@Bean
	public RedisCacheManager redisCacheManager(){

		RedisCacheManager cacheManager = new RedisCacheManager();
		cacheManager.setRedisManager(redisManager());
		cacheManager.setKeySerializer(new StringRedisSerializer());
		cacheManager.setValueSerializer(new RedisObjectSerializer());
		return cacheManager;
	}

	/**
	 * 配置shiro redisManager
	 * 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost("localhost");
		redisManager.setPort(6379);
		redisManager.setExpire(18000);// 配置缓存过期时间
		redisManager.setTimeout(0);
		// redisManager.setPassword(password);
		return redisManager;
	}




    /**
	 * Session Manager
	 * 使用的是shiro-redis开源插件
	 */

/*	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO());

//		sessionManager.setGlobalSessionTimeout(1800);
//		sessionManager.setCacheManager(cacheManager());
		return sessionManager;
	}*/

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    public DefaultHeaderSessionManager sessionManager(){
        DefaultHeaderSessionManager defaultHeaderSessionManager = new DefaultHeaderSessionManager();
        defaultHeaderSessionManager.setSessionDAO(redisSessionDAO());
        defaultHeaderSessionManager.setSessionValidationSchedulerEnabled(false);
        return defaultHeaderSessionManager;
    }
	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis
	 * 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		return redisSessionDAO;
	}

	/**
	 * 限制同一账号登录同时登录人数控制
	 *
	 * @return
	 */
	@Bean
	public KickoutSessionControlFilter kickoutSessionControlFilter() {
		KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
		kickoutSessionControlFilter.setCacheManager(redisCacheManager());
		kickoutSessionControlFilter.setSessionManager(sessionManager());
		kickoutSessionControlFilter.setKickoutAfter(false);
		kickoutSessionControlFilter.setMaxSession(1);
		kickoutSessionControlFilter.setKickoutUrl("/auth/kickout");
		return kickoutSessionControlFilter;
	}
	/**
	 * 加密算法
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");//采用MD5 进行加密
		hashedCredentialsMatcher.setHashIterations(1);//加密次数
		return hashedCredentialsMatcher;
	}
	
	/**
	 * 记住我的配置
	 * @return
	 */
/*	@Bean
	public RememberMeManager rememberMeManager() {
		Cookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);//通过js脚本将无法读取到cookie信息
        cookie.setMaxAge(60 * 60 * 24);//cookie保存一天
		CookieRememberMeManager manager=new CookieRememberMeManager();
		manager.setCookie(cookie);
		return manager;
	}*/

	/**
	 * 记住我的配置
	 * @return
	 */
	@Bean(name = "rememberMeManager")
	public HeaderRememberMeManager rememberMeManager() {
		HeaderRememberMeManager headerRememberMeManager = new HeaderRememberMeManager();
		// base64Encoded 自行生成一个 用于rememberMe加密
		headerRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
		return headerRememberMeManager;
	}
//	/**
//	 * 缓存配置
//	 * @return
//	 */
//	@Bean
//	public CacheManager shiroCacheManager() {
//		MemoryConstrainedCacheManager cacheManager=new MemoryConstrainedCacheManager();//使用内存缓存
//		return cacheManager;
//	}
	
	/**
	 * 配置realm，用于认证和授权
	 * @param hashedCredentialsMatcher
	 * @return AuthorizingRealm
	 */
	@Bean
	public MyShiroRealm shiroRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
		MyShiroRealm shiroRealm = new MyShiroRealm();
		//校验密码用到的算法
		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
		return shiroRealm;
	}
	
	/**
	 * 启用shiro方言，这样能在页面上使用shiro标签
	 * @return
	 */
	@Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


	/***
	 * 授权所用配置
	 *
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}


	/***
	 * 使授权注解起作用不如不加配置可以在pom文件中加入
	 * <dependency>
	 *<groupId>org.springframework.boot</groupId>
	 *<artifactId>spring-boot-starter-aop</artifactId>
	 *</dependency>
	 * @param securityManager
	 * @return
	 */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * 注册全局异常处理
     * @return
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver(){
        return new GlobalExceptionResolver();
    }
	/**
	 * Shiro生命周期处理器
	 *
	 */


}

package com.myIsoland.aop;

import com.myIsoland.annotation.DataSource;
import com.myIsoland.multiple.DataSourceContextHolder;
import groovy.util.logging.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Slf4j
@Aspect
@Order(-1)
public class DataSourceAspect {
    private static Logger logger= LoggerFactory.getLogger(DataSourceAspect.class);
    @Pointcut("@within(com.myIsoland.annotation.DataSource) || @annotation(com.myIsoland.annotation.DataSource)")
    public void pointCut(){

    }

    @Before("pointCut() && @annotation(dataSource)")
    public void doBefore(DataSource dataSource){
        logger.info("选择数据源---"+dataSource.value().getValue());
        DataSourceContextHolder.setDataSource(dataSource.value().getValue());
    }

    @After("pointCut()")
    public void doAfter(){
        DataSourceContextHolder.clear();
    }
}
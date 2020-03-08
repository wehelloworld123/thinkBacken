package com.myIsoland.common.component;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

// 解决SpringBoot不能再Quartz中注入Bean的问题
@Component
public final class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private transient AutowireCapableBeanFactory autowireCapablebeanFactory;

    @Override
    public void setApplicationContext(final ApplicationContext context) {
        autowireCapablebeanFactory= context.getAutowireCapableBeanFactory();
        }

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
         final Object job = super.createJobInstance(bundle);
         autowireCapablebeanFactory.autowireBean(job);
         return job;
         }
}

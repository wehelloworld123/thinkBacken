package com.myIsoland.common.handler;

import cn.hutool.core.date.DateTime;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.myIsoland.enitity.system.TsysUser;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import com.myIsoland.shiro.util.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

@Component
public class MetaHandler implements MetaObjectHandler {

    private static final Logger logger = LoggerFactory.getLogger(MetaHandler.class);

    /**
     * 新增数据执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        TsysUser userEntity = ShiroUtils.getUser();
        this.setFieldValByName("createTime", new DateTime(), metaObject);
        this.setFieldValByName("createBy", userEntity.getId(), metaObject);
        this.setFieldValByName("updateTime", new DateTime(), metaObject);
//        this.setFieldValByName("updateBy", userEntity.getLoginName(), metaObject);
    }

    /**
     * 更新数据执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        TsysUser userEntity = ShiroUtils.getUser();
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userEntity.getId(), metaObject);
    }

}
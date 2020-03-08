package com.myIsoland.common.handler;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.system.TsysUser;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import com.myIsoland.shiro.util.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MetaHandler implements MetaObjectHandler {

    private static final Logger logger = LoggerFactory.getLogger(MetaHandler.class);

    /**
     * 新增数据执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date date = DateUtils.getNowDate();
        this.setFieldValByName("createBy", ShiroUtils.getUserId(), metaObject);
        this.setFieldValByName("createDat",date, metaObject);
        this.setFieldValByName("lUpdateDat", date, metaObject);
    }

    /**
     * 更新数据执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        TsysUser userEntity = ShiroUtils.getUser();
        this.setFieldValByName("lUpdateDat", DateUtils.getNowDate(), metaObject);
//        this.setFieldValByName("updateBy", userEntity.getId(), metaObject);
    }

}
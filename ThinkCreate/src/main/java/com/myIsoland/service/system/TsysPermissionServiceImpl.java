package com.myIsoland.service.system;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.annotation.DataSource;
import com.myIsoland.enitity.system.TsysPermission;
import com.myIsoland.enums.DataSourceEnum;
import com.myIsoland.mapper.system.TsysPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TsysPermissionServiceImpl extends ServiceImpl<TsysPermissionMapper,TsysPermission> implements TsysPermissionService{

    @DataSource(DataSourceEnum.DB1)
    @Override
    public List<TsysPermission> GetPermissionByRoleid(String roleid) {
        return this.baseMapper.queryRoleId(roleid);
    }
}

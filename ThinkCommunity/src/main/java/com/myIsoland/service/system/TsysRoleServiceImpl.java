package com.myIsoland.service.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.annotation.DataSource;
import com.myIsoland.enitity.system.TsysRole;
import com.myIsoland.enums.DataSourceEnum;
import com.myIsoland.mapper.system.TsysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TsysRoleServiceImpl extends ServiceImpl<TsysRoleMapper,TsysRole> implements TsysRoleService{

    @DataSource(DataSourceEnum.DB1)
    @Override
    public List<TsysRole> getRolesById(String userid) {
        return this.baseMapper.queryUserRole(userid);
    }
}

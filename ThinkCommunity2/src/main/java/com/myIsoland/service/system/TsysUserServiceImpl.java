package com.myIsoland.service.system;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.annotation.DataSource;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.DataSourceEnum;
import com.myIsoland.mapper.system.TsysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TsysUserServiceImpl extends ServiceImpl<TsysUserMapper,TsysUser> implements TsysUserService{

    @DataSource(DataSourceEnum.DB1)
    @Override
    public TsysUser getUserByName(String username){
        return this.baseMapper.queryUserByName(username);
    }

}

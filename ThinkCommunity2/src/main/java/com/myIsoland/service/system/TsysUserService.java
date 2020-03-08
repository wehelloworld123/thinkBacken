package com.myIsoland.service.system;


import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.system.TsysUser;

public interface TsysUserService extends IService<TsysUser> {
    /**
     * 根据用户名字查询用户
     * @param username
     * @return
     */
    public TsysUser getUserByName(String username);
}

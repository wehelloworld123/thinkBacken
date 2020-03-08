package com.myIsoland.service.system;


import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.system.TsysRole;

import java.util.List;

public interface TsysRoleService extends IService<TsysRole> {
    /**
     * 根据用户id查询角色
     * @param userid
     * @return
     */
    public List<TsysRole> getRolesById(String userid);
}

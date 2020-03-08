package com.myIsoland.service.system;


import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.system.TsysPermission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TsysPermissionService  extends IService<TsysPermission> {
    /**
     * 根据角色id查询权限
     * @param roleid
     * @return
     */

    public List<TsysPermission> GetPermissionByRoleid(String roleid);
}

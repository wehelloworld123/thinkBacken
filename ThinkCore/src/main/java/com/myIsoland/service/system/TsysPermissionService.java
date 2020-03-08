package com.myIsoland.service.system;


import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.system.TsysPermission;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TsysPermissionService  extends IService<TsysPermission> {
    /**
     * 根据角色id查询权限
     * @param roleid
     * @return
     */

    public List<TsysPermission> getPermissionByRoleid(String roleid);
}

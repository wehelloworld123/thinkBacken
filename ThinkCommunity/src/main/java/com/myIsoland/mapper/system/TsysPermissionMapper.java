package com.myIsoland.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.system.TsysPermission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface TsysPermissionMapper extends BaseMapper<TsysPermission> {


    /**
     * 根据角色id查询权限
     * @param roleid
     * @return
     */
//    @Select("SELECT r.id,r.name FROM t_sys_permission p LEFT JOIN t_sys_permission_role pr ON p.id = pr.permission_id WHERE pr.role_id = #{roleid}")
//    @ResultMap("com.myIsoland.entity.system.TsysRole")

//    @Select("SELECT r.id,r.name FROM t_sys_permission p LEFT JOIN t_sys_permission_role pr ON p.id = pr.permission_id WHERE pr.role_id = #{roleid}")
//    @Results(id = "permMap",value =
//            {
//
//                    @Result(property = "")
//            })
    List<TsysPermission> queryRoleId(String roleid);
}

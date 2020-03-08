package com.myIsoland.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.system.TsysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TsysRoleMapper extends BaseMapper<TsysRole> {
    /**
     * 根据用户id查询角色
     * @param userid
     * @return
     */
//    @Select("SELECT r.id,r.name " +
//            "FROM t_sys_role r LEFT JOIN t_sys_role_user ru " +
//            "ON r.id = ru.sys_role_id" +
//            "WHERE ru.sys_user_id = #{userid}")
//    @ResultMap("com.myIsoland.entity.system.TsysRole")
    List<TsysRole> queryUserRole(String userid);
}

package com.myIsoland.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.system.TsysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsysUserMapper extends BaseMapper<TsysUser> {
//    @Select("SELECT * FROM t_sys_user WHERE username = #{username}")
//    @ResultMap("com.myIsoland.entity.system.TsysUser")
    TsysUser queryUserByName(String username);
}

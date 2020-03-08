package com.myIsoland.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.system.TsysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface TsysUserMapper extends BaseMapper<TsysUser> {
//    @Select("SELECT * FROM t_sys_user WHERE username = #{username}")
//    @ResultMap("com.myIsoland.entity.system.TsysUser")
    TsysUser queryUserByName(String username);
}

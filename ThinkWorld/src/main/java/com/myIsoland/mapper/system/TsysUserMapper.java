package com.myIsoland.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.system.TsysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
@Mapper
public interface TsysUserMapper extends BaseMapper<TsysUser> {
    @Select("SELECT * FROM t_sys_user WHERE username = #{username} AND is_del = 0")
    @Results(value =
            {
                    @Result(property = "id",column = "id"),
                    @Result(property = "username",column = "username"),
                    @Result(property = "password",column = "password"),
                    @Result(property = "nickname",column = "nickname"),
                    @Result(property = "email",column = "email"),
                    @Result(property = "avatar",column = "avatar"),
                    @Result(property = "sex",column = "sex"),
                    @Result(property = "describe",column = "describe"),
            })
    TsysUser queryUserByName(String username);
}

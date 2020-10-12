package com.myIsoland.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.system.TsysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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
                    @Result(property = "description",column = "description"),
            })
    TsysUser queryUserByName(String username);

    @Select("SELECT id,nickname,avatar,sex,description FROM t_sys_user WHERE id = #{id} AND is_del = 0")
    @Results(value =
            {
                    @Result(property = "id",column = "id"),
                    @Result(property = "username",column = "username"),
                    @Result(property = "password",column = "password"),
                    @Result(property = "nickname",column = "nickname"),
                    @Result(property = "email",column = "email"),
                    @Result(property = "avatar",column = "avatar"),
                    @Result(property = "sex",column = "sex"),
                    @Result(property = "description",column = "description"),
            })
    Map queryUserInfoById(Map map);
}

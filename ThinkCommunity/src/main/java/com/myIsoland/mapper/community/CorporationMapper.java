package com.myIsoland.mapper.community;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.community.Corporation;
import com.myIsoland.enitity.system.TsysUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CorporationMapper extends BaseMapper<Corporation> {
    @Update("UPDATE t_com_corporation SET like=like+1 WHERE uid =#{uid} AND is_del =0")
    int updateCorpLike(String uid);

    @Select("SELECT u.id,u.nickname,u.avatar,u.sex" +
            "FROM t_sys_user u LEFT JOIN t_com_user_crop uc" +
            "ON uc.c_uid=#{corpId} AND uc.is_del=0 AND uc.state=1 " +
            "WHERE u.id=uc.u_uid AND uc.is_del=0")
    @Results(id = "userInfo",value = {
            @Result(property = "nickname",column = "nickname"),
            @Result(property = "id",column = "id"),
            @Result(property = "avatar",column = "avatar"),
            @Result(property = "sex",column = "sex")
    })
    List<TsysUser> selectCorpUser(String corpId);
}

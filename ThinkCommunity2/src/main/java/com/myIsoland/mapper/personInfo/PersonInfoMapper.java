package com.myIsoland.mapper.personInfo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.personInfo.PersonInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface PersonInfoMapper extends BaseMapper<PersonInfo> {

    @Select("SELECT i.uid as uid,i.discuss as discuss,i.corporations as corporations,i.activities as activities,i.concern as concern," +
            "u.nickname as nickname,u.avatar as avatar,u.sex as sex,u.description as description " +
            "FROM t_percom_info as i INNER JOIN t_sys_user as u " +
            "ON i.uid = #{userId} " +
            "AND i.uid = u.id " +
            "WHERE i.is_del = 0 " +
            "AND u.is_del = 0 ")
    @Results(value =
            {
                @Result(property = "uid",column = "uid"),
                @Result(property = "discuss",column = "discuss"),
                @Result(property = "corporations",column = "corporations"),
                @Result(property = "activities",column = "activities"),
                @Result(property = "concern",column = "concern"),
                @Result(property = "nickname",column = "nickname"),
                @Result(property = "avatar",column = "avatar"),
                @Result(property = "sex",column = "sex"),
                @Result(property = "description",column = "description")
            })

    PersonInfo selectPersonInfo(@Param("userId") String userId);
}

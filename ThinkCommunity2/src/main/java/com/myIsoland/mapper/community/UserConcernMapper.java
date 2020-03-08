package com.myIsoland.mapper.community;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.community.UserConcern;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserConcernMapper extends BaseMapper<UserConcern> {
    @Update("UPDATE t_com_user_concern SET is_del=1,l_update_dat = now() WHERE did =#{discussId} AND uid =#{userId};")
    int delUserDicuss(Long discussId, String userId);

    @Update("UPDATE t_com_user_concern SET is_del=1,l_update_dat = now() WHERE aid =#{activityId} AND uid =#{userId};")
    int delUserActivity(String activityId, String userId);

    @Select("SELECT count(id) as isCon " +
            "FROM t_com_user_concern " +
            "WHERE did =#{id} " +
            "AND uid =#{userId} " +
            "AND is_del=0")
    int queryDisCon(String id, String userId);
}

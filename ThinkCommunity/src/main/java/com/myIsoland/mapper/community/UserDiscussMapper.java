package com.myIsoland.mapper.community;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.community.UserDiscuss;
import org.apache.ibatis.annotations.Update;

public interface UserDiscussMapper extends BaseMapper<UserDiscuss> {
    @Update("UPDATE t_com_user_discuss SET is_del=1 WHERE d_id =#{discussId} AND u_uid =#{userId};")
    int delUserDicuss(Integer discussId,String userId);
}

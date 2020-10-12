package com.myIsoland.mapper.community;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.community.UserCorportion;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface UserCorpMapper extends BaseMapper<UserCorportion> {
    @Select("SELECT count(id) as isPart " +
            "FROM t_com_user_corp " +
            "WHERE cid = #{id} " +
            "AND uid = #{userid} " +
            "AND status = 1 " +
            "AND is_del = 0")
    int queryJionCorp(Map map);
}

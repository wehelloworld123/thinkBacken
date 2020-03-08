package com.myIsoland.mapper.debate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.enitity.debate.UserTopic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserTopicMapper extends BaseMapper<UserTopic> {
    @Select("SELECT count(rank) as number,rank " +
            "FROM t_deb_user_topic " +
            "WHERE create_by = #{userId} " +
            "AND is_del = 0 " +
            "GROUP BY rank " +
            "HAVING rank in(1,2,3) ")
    List<Map<String,Object>> queryUserRankAns(String userId);

    @Select("SELECT GROUP_CONCAT(DISTINCT t.uid) as uid,t.title as title,t.year as year,t.times as times,ut.create_dat as createDat " +
            "FROM t_deb_user_topic as ut RIGHT JOIN t_deb_topic as t " +
            "ON ut.create_by = #{userId} " +
            "AND ut.create_dat < #{date}" +
            "AND ut.is_del = 0 " +
            "AND ut.topic_id = t.uid " +
            "ORDER BY ut.create_by DESC " +
            "LIMIT 5")
    @Results({
            @Result(column = "uid",property = "uid"),
            @Result(column = "title",property = "title"),
            @Result(column = "year",property = "year"),
            @Result(column = "times",property = "times"),
            @Result(column = "create_dat",property = "createDat")
    })
    List<Topic> GetUserTopics(@Param("userId") String userId, @Param("date") Date date);

}

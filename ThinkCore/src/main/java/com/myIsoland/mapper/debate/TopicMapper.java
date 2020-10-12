package com.myIsoland.mapper.debate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.debate.Topic;
import com.myIsoland.model.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

public interface TopicMapper extends BaseMapper<Topic> {
    @Select("SELECT t.uid as uid, t.subject as subject,an.no as no,an.creator as creator,an.creator_avat as creatorAvat,an.create_by as createBy" +
            "FROM t_deb_answer as an LEFT JOIN  t_deb_topic as t " +
            "ON t.year=#{year} AND t.times=#{times} " +
            "WHERE an.topic_id=t.uid AND an.is_del=0 " +
            "ORDER BY an.grade+0,an.create_dat DESC")
    List<Map<String,Object>> queryThreeAns(String year, int times);

    @Select("SELECT uid,title,content,view,belong_nam,year,time " +
            "FROM t_deb_topic " +
            "WHERE finish=1 AND id_del=0 " +
            "ORDER BY answer,answers DESC limit 3")
    @Results(id = "topic",value = {
            @Result(column = "uid",property = "uid"),
            @Result(column = "title",property = "title"),
            @Result(column = "content",property = "content"),
            @Result(column = "view",property = "view"),
            @Result(column = "belong_nam",property = "belongName"),
            @Result(column = "year",property = "year"),
            @Result(column = "times",property = "times")
    })
    List<Topic> queryHotTopic();

/*    @Select("SELECT uid,title,content,view,belong_nam,year,time " +
            "FROM t_deb_topic " +
            "WHERE finish=1 AND id_del=0 " +
            "ORDER BY answer,answers DESC limit 3")
    @Results(value = {
            @Result(column = "uid",property = "uid"),
            @Result(column = "title",property = "title"),
            @Result(column = "content",property = "content"),
            @Result(column = "views",property = "view"),
            @Result(column = "answers",property = "answers"),
            @Result(column = "belong_nam",property = "belongName"),
            @Result(column = "belong_nam",property = "belongName"),
            @Result(column = "year",property = "year"),
            @Result(column = "times",property = "times"),
            @Result(property = "answerList",column = "uid",many = @Many(select="com.myIsoland.mapper.debate.AnswerMapper.queryComment",fetchType = FetchType.EAGER))
    })
    Topic queryTopicById();*/

}
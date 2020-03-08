package com.myIsoland.mapper.debate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.debate.Answer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AnswerMapper extends BaseMapper<Answer> {
    @Select("SELECT no,summary,likes,recommend,creator,creator_avat,create_dat,create_by " +
            "FROM t_deb_answer " +
            "WHERE topic_id = #{topicId} " +
            "AND create_dat < #{date}" +
            "ORDER BY create_dat DESC " +
            "LIMIT #{start},10")
    @Results({
            @Result(column = "no",property = "no"),
            @Result(column = "summary",property = "summary"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "creator_avat",property = "creatorAvat"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "create_by",property = "createBy"),
            @Result(property = "recommends",column = "no",many = @Many(select="com.myIsoland.mapper.debate.AnswerMapper.queryFontRecom",fetchType = FetchType.EAGER))
    })
    List<Answer> queryAnsAndRecById(@Param("topicId") String topicId, @Param("date") Date date, @Param("start") int start);


    @Select("SELECT no,summary,likes,recommend,creator,creator_avat,create_dat,create_by " +
            "FROM t_deb_answer " +
            "WHERE topic_id = #{topicId} " +
            "AND create_dat < #{date} " +
            "AND likes >= 100 " +
            "AND recommend > = 40" +
            "ORDER BY grade DESC " +
            "LIMIT #{start},10")
    @Results({
            @Result(column = "no",property = "no"),
            @Result(column = "summary",property = "summary"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "creator_avat",property = "creatorAvat"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "create_by",property = "createBy"),
            @Result(property = "recommends",column = "no",many = @Many(select="com.myIsoland.mapper.debate.AnswerMapper.queryFontRecom",fetchType = FetchType.EAGER))
    })
    List<Answer> queryHotAnsAndRecById(@Param("topicId") String topicId, @Param("date") Date date, @Param("start") int start);

    @Update("update t_deb_debate SET likes = likes + 1 WHERE no = #{ansId}")
    int updateAnsLike(int ansId);

}

package com.myIsoland.mapper.debate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.debate.Answer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AnswerMapper extends BaseMapper<Answer> {
    @Select("SELECT no,substring(content,1,300) as content,conclusion,likes,recommend,creator,creator_avat,create_dat,create_by " +
            "FROM t_deb_answer " +
            "WHERE topic_id = #{topicId} " +
            "AND create_dat < #{date}" +
            "ORDER BY create_dat DESC " +
            "LIMIT #{start},#{limit}")
    @Results({
            @Result(column = "no",property = "no"),
            @Result(column = "content",property = "content"),
            @Result(column = "conclusion",property = "conclusion"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "creator_avat",property = "creatorAvat"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "create_by",property = "createBy"),
            @Result(property = "recommends",column = "no",many = @Many(select="com.myIsoland.mapper.debate.AnswerMapper.queryFontRecom",fetchType = FetchType.EAGER))
    })
    List<Answer> queryAnsAndRecByDate(@Param("topicId") String topicId, @Param("date") Date date, @Param("start") int start, @Param("limit") int limit);


    @Select("SELECT no,substring(content,1,300) as content,conclusion,likes,recommend,creator,creator_avat,create_dat,create_by " +
            "FROM t_deb_answer " +
            "WHERE topic_id = #{topicId} " +
            "AND create_dat < #{date} " +
            "AND likes >= #{likes} " +
            "AND recommend > = #{recoms}" +
            "ORDER BY likes DESC " +
            "LIMIT #{start},#{limit}")
    @Results({
            @Result(column = "no",property = "no"),
            @Result(column = "content",property = "content"),
            @Result(column = "conclusion",property = "conclusion"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "recommend",property = "recommend"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "creator_avat",property = "creatorAvat"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "create_by",property = "createBy"),
            @Result(property = "recommends",column = "no",many = @Many(select="com.myIsoland.mapper.debate.AnswerMapper.queryFontRecom",fetchType = FetchType.EAGER))
    })
    List<Answer> queryHotAnsAndRecByTopicId(@Param("topicId") String topicId,@Param("date")Date date, @Param("likes") int likes,@Param("recoms")int recoms,@Param("start") int start, @Param("limit") int limit);

/*    @Update("update t_deb_debate SET likes = likes + 1 WHERE no = #{ansId}")
    int updateAnsLike(int ansId);*/
    @Update("UPDATE t_deb_debate " +
            "SET likes = likes + 1,favorer = CONCAT(favorer,#{userId}),l_update_dat = now() " +
            "WHERE no = #{no} " +
            "AND is_del = 0 ")
    int updateLikeSts(@Param("userId")String userId,@Param("no")String no);

    @Update("UPDATE t_deb_debate " +
            "SET likes = likes - 1,favorer = replace(favorer,#{userId},''),l_update_dat = now() " +
            "WHERE no = #{no} " +
            "AND is_del = 0 ")
    int delLikeSts(@Param("userId") String userId,@Param("no") String no);
}

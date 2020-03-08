package com.myIsoland.mapper.community;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.myIsoland.enitity.community.Comment;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("SELECT id ,content,root_id,reply_id,reply_name,likes,creator,creator_sex,creator_avat," +
            "create_by,create_dat,instr(favorer,#{userid}) as is_like " +
            "FROM t_com_comment " +
            "WHERE root_id = #{id} " +
            "AND create_dat <=#{date} " +
            "AND is_del=0 " +
            "ORDER BY create_dat DESC " +
            "LIMIT 0,20")
    @Results(value =
            {
            @Result(property = "id",column = "id"),
            @Result(property = "content",column = "content"),
            @Result(property = "likes",column = "likes"),
            @Result(property = "rootId",column = "root_id"),
            @Result(property = "replyId",column = "reply_id"),
            @Result(property = "replyName",column = "reply_name"),
            @Result(property = "creator",column = "creator"),
            @Result(property = "creatorSex",column = "creator_sex"),
            @Result(property = "creatorAvat",column = "creator_avat"),
            @Result(property = "createBy",column = "create_by"),
            @Result(property = "createDat",column = "create_dat")
    })
    List<Comment> queryComment(Map map);

    @Select("SELECT id ,content,root_id,reply_id,reply_name,likes,creator,creator_sex,creator_avat,create_by,create_dat " +
            "FROM t_com_comment " +
            "WHERE root_id = #{rootId} AND create_dat<date AND is_del=0 " +
            "LIMIT #{number}")
    @Results(value =
            {
                    @Result(property = "id",column = "id"),
                    @Result(property = "content",column = "content"),
                    @Result(property = "likes",column = "likes"),
                    @Result(property = "rootId",column = "root_id"),
                    @Result(property = "replyId",column = "reply_id"),
                    @Result(property = "replyName",column = "reply_name"),
                    @Result(property = "creator",column = "creator"),
                    @Result(property = "creatorSex",column = "creator_sex"),
                    @Result(property = "creatorAvat",column = "creator_avat"),
                    @Result(property = "createBy",column = "create_by"),
                    @Result(property = "createDat",column = "create_dat")
            })
    List<Comment> queryCommentByDate(@Param("rootId")Long id, @Param("date")DateTime date,@Param("number")int number);

    @Select("SELECT id ,content,root_id,reply_id,reply_name,likes,creator,creator_sex,creator_avat," +
            "create_by,create_dat,instr(favorer,#{userid}) as is_like " +
            "FROM t_com_comment " +
            "WHERE root_id = #{id} " +
            "AND likes >= 100  " +
            "AND is_del = 0 " +
            "AND is_del = 0 " +
            "ORDER BY create_dat DESC " +
            "LIMIT 1")
    @Results(value =
            {
                    @Result(property = "id",column = "id"),
                    @Result(property = "content",column = "content"),
                    @Result(property = "likes",column = "likes"),
                    @Result(property = "isLike",column = "is_like"),
                    @Result(property = "rootId",column = "root_id"),
                    @Result(property = "replyId",column = "reply_id"),
                    @Result(property = "replyName",column = "reply_name"),
                    @Result(property = "creator",column = "creator"),
                    @Result(property = "creatorSex",column = "creator_sex"),
                    @Result(property = "creatorAvat",column = "creator_avat"),
                    @Result(property = "createBy",column = "create_by"),
                    @Result(property = "createDat",column = "create_dat")
            })
    Comment queryHotComment(Map map);
    /**
     *@Author:THINKPAD
     *@Description:更新喜欢状态
     * @param userId
     * @param no
     *@Return:int
     *@Data:23:01 2020/2/7
     **/
    @Update("UPDATE t_com_comment " +
            "SET likes = likes + 1,favorer = CONCAT(favorer,#{userId}),l_update_dat = now() " +
            "WHERE id = #{id} " +
            "AND is_del = 0 ")
    int updateLikeSts(@Param("userId") String userId,@Param("id") Long id);

    @Update("UPDATE t_com_comment " +
            "SET likes = likes - 1,favorer = replace(favorer,#{userId},''),l_update_dat = now() " +
            "WHERE id = #{id} " +
            "AND is_del = 0 ")
    int delLikeSts(@Param("userId") String userId,@Param("id") Long id);


    /**
     *@Author:THINKPAD
     *@Description:查询用户回复信息
     * @param userId
     * @param date
     * @param page
     * @param number
     *@Return:java.util.List<com.myIsoland.enitity.community.Comment>
     *@Data:19:50 2020/2/15
     **/
    @Select("SELECT DISTINCT t.id as id ,t.content as content,t.root_id as root_id,t.reply_id as reply_id,t.reply_name as reply_name," +
            "t.likes as likes,t.creator as creator,t.creator_sex as create_sex,t.creator_avat as create_avat,t.create_dat as create_dat," +
            "d.creator as root_name,d.content as root_content " +
            "FROM t_com_comment as t INNER JOIN t_com_discuss as d " +
            "ON t.reply_id = #{userId} " +
            "OR  t.create_by = #{userId}  " +
            "AND t.root_id = d.id " +
            "AND t.create_dat <= #{date} " +
            "AND t.is_del = 0 " +
            "ORDER BY t.create_dat DESC " +
            "LIMIT #{page},#{number}")
    @Results(value =
            {
                    @Result(property = "id",column = "id"),
                    @Result(property = "content",column = "content"),
                    @Result(property = "likes",column = "likes"),
                    @Result(property = "rootId",column = "root_id"),
                    @Result(property = "replyId",column = "reply_id"),
                    @Result(property = "replyName",column = "reply_name"),
                    @Result(property = "creator",column = "creator"),
                    @Result(property = "creatorSex",column = "creator_sex"),
                    @Result(property = "creatorAvat",column = "creator_avat"),
                    @Result(property = "createBy",column = "create_by"),
                    @Result(property = "createDat",column = "create_dat"),
                    @Result(property = "rootName",column = "root_name"),
                    @Result(property = "rootContent",column = "root_content")
            })
    List<Comment> queryUserComment(@Param("userId") String userId,@Param("date") Date date,@Param("page") int page,@Param("number") int number);


    @Insert("<script>"+
            "insert into t_com_comment (id,likes,favorer,l_update_dat) " +
            "values " +
            "<foreach collection='data' item='param1' separator=','>" +
            " (#{param1.id},#{param1.likes},#{param1.favorer},now()) " +
            " </foreach> " +
            "on duplicate key " +
            "update likes=likes+values(likes),favorer = CONCAT(favorer,values(favorer)),l_update_dat=now(); " +
            "</script>")
    int batchUpdateLikeSts(@Param("data") List<Comment> data);
}

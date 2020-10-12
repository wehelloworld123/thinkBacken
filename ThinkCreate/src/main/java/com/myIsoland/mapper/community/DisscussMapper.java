package com.myIsoland.mapper.community;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.community.Disscuss;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;

public interface DisscussMapper extends BaseMapper<Disscuss> {


    @Select("SELECT  d.id as id,d.content as content,d.pic as pic,d.location as location,d.label as label,d.likes as likes,instr(d.favorer,#{userId}) as is_like,#{userId} as userId, " +
            "d.comment_no as comment_no,u.username as creator,u.sex as creator_sex,u.avatar as creator_avat,d.create_dat as create_dat,d.l_update_dat as l_update_dat " +
            "FROM t_com_discuss d INNER JOIN t_sys_user u " +
            "ON d.create_by = u.id " +
            "AND d.likes < #{likes} " +
            "OR (d.likes = #{likes} AND d.l_update_dat<#{update} ) " +
            "AND u.is_del = 0 " +
            "AND d.is_del = 0 " +
            "ORDER BY d.likes DESC,d.l_update_dat DESC " +
            "LIMIT #{number} ")
    @Results(value =
            {
                    @Result(property = "id",column = "id"),
                    @Result(property = "content",column = "content"),
                    @Result(property = "pic",column = "pic"),
                    @Result(property = "location",column = "location"),
                    @Result(property = "label",column = "label"),
                    @Result(property = "likes",column = "likes"),
                    @Result(property = "isLike",column = "is_like"),
                   // @Result(property = "isCon",column = "{id=id,userId=userId}",one = @One(select="com.myIsoland.mapper.community.UserConcernMapper.queryDisCon",fetchType = FetchType.EAGER)),
                    @Result(property = "commentNo",column = "comment_no"),
                    @Result(property = "creator",column = "creator"),
                    @Result(property = "creatorSex",column = "creator_sex"),
                    @Result(property = "creatorAvat",column = "creator_avat"),
                    @Result(property = "createBy",column = "create_by"),
                    @Result(property = "createDat",column = "create_dat"),
                    @Result(property = "lUpdateDat",column = "l_update_dat")
            })
    List<Disscuss> queryHotDiscussByLike(@Param("userId") String userId, @Param("likes") int likes, @Param("update") Date update, @Param("number") int number);


    @Select("SELECT  d.id as id,d.content as content,d.pic as pic,d.location as location,d.label as label,d.likes as likes,instr(d.favorer,#{userId}) as is_like,#{userId} as userId, " +
            "d.comment_no as comment_no,u.username as creator,u.sex as creator_sex,u.avatar as creator_avat,d.create_dat as create_dat,d.l_update_dat as l_update_dat  " +
            "FROM t_com_discuss d left JOIN t_sys_user u " +
            "ON d.create_by = u.id "+
            "AND u.is_del = 0 " +
            "WHERE d.create_dat <= #{date} " +
            "AND d.source = #{source}"+
            "AND d.is_del = 0 " +
            "ORDER BY d.create_dat DESC " )
    @Results(value =
            {
                    @Result(property = "id",column = "id"),
                    @Result(property = "content",column = "content"),
                    @Result(property = "pic",column = "pic"),
                    @Result(property = "location",column = "location"),
                    @Result(property = "label",column = "label"),
                    @Result(property = "likes",column = "likes"),
                    @Result(property = "isLike",column = "is_like"),
                    //@Result(property = "isCon",column = "{id=id,userId=userId}",one = @One(select="com.myIsoland.mapper.community.UserConcernMapper.queryDisCon",fetchType = FetchType.EAGER)),
                    @Result(property = "commentNo",column = "comment_no"),
                    @Result(property = "creator",column = "creator"),
                    @Result(property = "creatorSex",column = "creator_sex"),
                    @Result(property = "creatorAvat",column = "creator_avat"),
                    @Result(property = "createBy",column = "create_by"),
                    @Result(property = "createDat",column = "create_dat"),
                    @Result(property = "lUpdateDat",column = "l_update_dat")
            })
    List<Disscuss> queryNewDiscussOrderByDate(@Param("userId") String userId, @Param("date") Date date,@Param("source") String source);


    /**
     *@Author:THINKPAD
     *@Description:根据用户id获取用户关注贴
     * @param userId
     *@Return:java.util.List<com.myIsoland.enitity.community.Disscuss>
     *@Data:12:41 2019/11/23
     **/
    @Select("SELECT d.id as id,d.content as content,d.pic as pic,d.location as location,d.label as label,d.likes as likes,instr(d.favorer,#{userId}) as is_like," +
            "d.comment_no as comment_no,d.creator as creator,d.creator_sex as creator_sex,d.creator_avat as creator_avat,d.create_dat as create_dat,d.l_update_dat as l_update_dat  " +
            "FROM t_com_discuss as d LEFT JOIN t_com_user_concern as u " +
            "ON u.did = d.id " +
            "AND u.is_del=0 " +
            "WHERE  u.uid=#{userId} " +
            "AND d.is_del = 0 " +
            "AND d.source = #{source}" +
            "AND u.create_dat <= #{date} " +
            "ORDER BY u.create_dat DESC " +
            "LIMIT #{start},#{number}")

    @Results(value =
            {
                    @Result(property = "id",column = "id"),
                    @Result(property = "content",column = "content"),
                    @Result(property = "pic",column = "pic"),
                    @Result(property = "location",column = "location"),
                    @Result(property = "label",column = "label"),
                    @Result(property = "likes",column = "likes"),
                    @Result(property = "isLike",column = "is_like"),
                    @Result(property = "commentNo",column = "comment_no"),
                    @Result(property = "creator",column = "creator"),
                    @Result(property = "creatorSex",column = "creator_sex"),
                    @Result(property = "creatorAvat",column = "creator_avat"),
                    @Result(property = "createBy",column = "create_by"),
                    @Result(property = "createDat",column = "create_dat"),
                    @Result(property = "lUpdateDat",column = "l_update_dat")
            })
    List<Disscuss> queryConcernDisById(@Param("userId") String userId, @Param("date") Date date, @Param("start") int start, @Param("number") int number,@Param("source") String source);


    @Select("SELECT d.id as id,d.content as content,d.pic as pic,d.location as location,d.label as label,d.likes as likes,instr(d.favorer,#{userid}) as is_like," +
            "d.comment_no as comment_no,d.creator as creator,d.creator_sex as creator_sex,d.creator_avat as creator_avat,#{userid} as userid,#{date} as date " +
            "FROM t_com_discuss as d INNER JOIN t_com_user_concern as u " +
            "ON d.id = #{id} AND d.id=u.did " +
            "AND u.uid=#{userid} " +
            "AND d,is_del = 0 " +
            "AND u.is_del = 0 ")
    @Results(value =
            {
                    @Result(property = "id",column = "id"),
                    @Result(property = "content",column = "content"),
                    @Result(property = "pic",column = "pic"),
                    @Result(property = "location",column = "location"),
                    @Result(property = "label",column = "label"),
                    @Result(property = "likes",column = "likes"),
                    @Result(property = "commentNo",column = "comment_no"),
                    @Result(property = "creator",column = "creator"),
                    @Result(property = "creatorSex",column = "creator_sex"),
                    @Result(property = "creatorAvat",column = "creator_avat"),
                    @Result(property = "status",column = "status"),
                    @Result(property = "isLike",column = "is_like"),
                    @Result(property = "comment",column = "{id=id,userid=userid}",one = @One(select="com.myIsoland.mapper.community.CommentMapper.queryHotComment",fetchType = FetchType.EAGER)),
                    @Result(property = "comments",column = "{id=id,userid=userid,date=date}",many = @Many(select="com.myIsoland.mapper.community.CommentMapper.queryComment",fetchType = FetchType.EAGER))
            })
    Disscuss queryDisccussInfo(@Param("id") Long id, @Param("userid") String userid, @Param("date") Date date);

    /**
     *@Author:THINKPAD
     *@Description:更新喜欢状态
     * @param userId
     * @param no
     *@Return:int
     *@Data:23:01 2020/2/7
     **/
    @Update("UPDATE t_com_discuss " +
            "SET likes = likes + 1,favorer = CONCAT(favorer,#{userId}),l_update_dat = now() " +
            "WHERE id = #{id} " +
            "AND is_del = 0 ")
    int updateLikeSts(@Param("userId") String userId, @Param("id") Long id);

    @Update("UPDATE t_com_discuss " +
            "SET likes = likes - 1,favorer = replace(favorer,#{userId},''),l_update_dat = now() " +
            "WHERE id = #{id} " +
            "AND is_del = 0 ")
    int delLikeSts(@Param("userId") String userId, @Param("id") Long id);

    @Insert("<script>"+
            "insert into t_com_discuss (id,likes,favorer,l_update_dat) " +
            "values " +
            "<foreach collection='data' item='param1' separator=','>" +
            " (#{param1.id},#{param1.likes},#{param1.favorer},now()) " +
            " </foreach> " +
            "on duplicate key " +
            "update likes=likes+values(likes),favorer = CONCAT(favorer,values(favorer)),l_update_dat=now(); " +
            "</script>")
    int batchUpdateLikeSts(@Param("data") List<Disscuss> data);

}

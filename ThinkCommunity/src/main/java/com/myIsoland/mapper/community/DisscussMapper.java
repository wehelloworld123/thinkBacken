package com.myIsoland.mapper.community;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.community.Disscuss;
import org.apache.ibatis.annotations.*;


import java.util.Date;
import java.util.List;
@Mapper
public interface DisscussMapper extends BaseMapper<Disscuss> {
    @Select("SELECT  id,content,pic,location,label,like,comment_no,creator,creator_sex.creator_avat" +
            "FROM t_com_discuss" +
            "WHERE is_del = 0" +
            "ORDER BY like,concern_no DESC LIMIT 40")
    @Results(id = "discuss",value =
            {
                    @Result(property = "id",column = "id"),
                    @Result(property = "content",column = "content"),
                    @Result(property = "pic",column = "pic"),
                    @Result(property = "location",column = "location"),
                    @Result(property = "label",column = "label"),
                    @Result(property = "like",column = "like"),
                    @Result(property = "commentNo",column = "comment_no"),
                    @Result(property = "creator",column = "creator"),
                    @Result(property = "creatorSex",column = "creator_sex"),
                    @Result(property = "creatorAvat",column = "creator_avat")
            })
    List<Disscuss> queryHotDiscuss();

    @Select("SELECT  id,content,pic,location,label,like,comment_no,creator,creator_sex.creator_avat" +
            "FROM t_com_discuss" +
            "WHERE like < #{like} AND " +
            "is_del = 0" +
            "ORDER BY like,concern_no DESC LIMIT 40")
    @ResultMap(value = "discuss")
    List<Disscuss> queryHotDiscuss(int like);

    @Select("SELECT  id,content,pic,location,label,like,comment_no,creator,creator_sex.creator_avat" +
            "FROM t_com_discuss" +
            "WHERE is_del = 0" +
            "ORDER BY create_dat ASC LIMIT 40")
    @ResultMap(value = "discuss")
    List<Disscuss> queryNewDiscuss();

    @Select("SELECT  id,content,pic,location,label,like,comment_no,creator,creator_sex.creator_avat " +
            "FROM com_discuss " +
            "WHERE create_dat < #{date} AND " +
            "is_del = 0 " +
            "ORDER BY create_dat ASC LIMIT 40")
    @ResultMap(value = "discuss")
    List<Disscuss> queryNewDiscuss(@Param("date") Date date);

//    @Select("SELECT  id,content,pic,location,label,like,comment_no,creator,creator_sex,creator_avat" +
//            "FROM t_com_discuss" +
//            "WHERE create_dat > #{date} AND" +
//            "is_del = 0" +
//            "ORDER BY create_dat ASC LIMIT 40")
//    @ResultMap(value = "discuss")
//    List<Disscuss> queryNewDiscuss(DateTime date);

    @Select("SELECT d.id,d.content,d.pic,d.location,d.label,d.like,d.comment_no,d.creator,d.creator_sex,d.creator_avat " +
            "FROM t_com_discuss as d LEFE JOIN t_com_user_discuss as u " +
            "ON u.u_uid=#{userId} AND u.is_del=0 " +
            "WHERE d.id=u.d_id AND d.is_del=0 " +
            "ORDER BY u.create_dat DESC LIMIT 40")
    @ResultMap(value = "discuss")
    List<Disscuss> queryConcernDis(@Param("userId") String userId);

    @Select("SELECT d.id,d.content,d.pic,d.location,d.label,d.like,d.comment_no,d.creator,d.creator_sex,d.creator_avat " +
            "FROM t_com_discuss as d LEFE JOIN t_com_user_discuss as u " +
            "ON u.u_uid=#{userId} AND u.create_da t> #{dateTime} AND u.is_del=0 " +
            "WHERE d.id=u.d_id AND d.is_del=0 " +
            "ORDER BY u.id DESC LIMIT 40")
    @ResultMap(value = "discuss")
    List<Disscuss> queryConcernDis(@Param("userId") String userId, @Param("dateTime") DateTime dateTime);
}

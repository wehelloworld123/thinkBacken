package com.myIsoland.mapper.community;
import cn.hutool.core.date.DateTime;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.community.Activity;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
    @Select("SELECT  uid,subject,pic,likes,corpor_id,organ " +
            "FROM t_com_activity " +
            "WHERE is_del = 0 AND finish = 0 " +
            "ORDER BY like DESC LIMIT 40")
    @Results(id = "activity",value =
            {
                    @Result(property = "uid",column = "uid"),
                    @Result(property = "subject",column = "subject"),
                    @Result(property = "pic",column = "pic"),
                    @Result(property = "likes",column = "likes"),
                    @Result(property = "corporId",column = "corpor_id"),
                    @Result(property = "like",column = "like"),
                    @Result(property = "organ",column = "organ")
            })
    List<Activity> queryHotActivity();

    @Select("SELECT uid,subject,pic,like,corpor_id,organ " +
            "FROM t_com_activity " +
            "WHERE like < #{like} AND " +
            "is_del = 0 AND finish = 0 " +
            "ORDER BY likes DESC LIMIT 40 ")
    @ResultMap(value = "activity")
    List<Activity> queryHotActivity(int like);

//    @Select("SELECT uid,subject,pic,likes,corpor_id,organ " +
//            "FROM t_com_activity " +
//            "WHERE is_del = 0 AND finish = 0 " +
//            "ORDER BY create_dat ASC LIMIT 40")
//    @ResultMap(value = "activity")
//    List<Activity> queryNewActivity();
//
//    @Select("SELECT uid,subject,pic,likes,corpor_id,organ " +
//            "FROM t_com_activity " +
//            "WHERE create_dat > #{date} AND " +
//            "is_del = 0 AND finish = 0 " +
//            "ORDER BY create_dat ASC LIMIT 40")
//    @ResultMap(value = "activity")
//    List<Activity> queryNewActivity(@Param("date") DateTime date);

//    @Select("SELECT d.id,d.content,d.pic,d.location,d.label,d.likes,d.comment_no,d.creator,d.creator_sex,d.creator_avat " +
//            "FROM t_com_activity as d LEFE JOIN t_com_user_discuss as u " +
//            "ON u.u_uid=#{userId} AND u.is_del=0 " +
//            "WHERE d.id=u.d_id AND d.is_del=0 " +
//            "ORDER BY u.create_dat DESC LIMIT 40")
//    @ResultMap(value = "activity")
//    List<Activity> queryConcernDis(@Param("userId") String userId);
//
//    @Select("SELECT d.id,d.content,d.pic,d.location,d.label,d.likes,d.comment_no,d.creator,d.creator_sex,d.creator_avat " +
//            "FROM t_com_activity as d LEFE JOIN t_com_user_discuss as u " +
//            "ON u.u_uid=#{userId} AND u.create_da t> #{dateTime} AND u.is_del=0 " +
//            "WHERE d.id=u.d_id AND d.is_del=0 " +
//            "ORDER BY u.id DESC LIMIT 40")
//    @ResultMap(value = "activity")
//    List<Activity> queryConcernDis(@Param("userId") String userId,@Param("dateTime") DateTime dateTime);
}
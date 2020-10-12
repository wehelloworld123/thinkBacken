package com.myIsoland.mapper.community;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.community.Activity;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
    @Select("SELECT uid,subject,pic,dat,address,views,corpor_id,organ,create_dat,l_update_dat " +
            "FROM t_com_activity " +
            "WHERE views < #{views} " +
            "OR (views = #{views} AND l_update_dat<#{update} ) " +
            "AND is_del = 0 AND finish = 0 " +
            "ORDER BY views DESC,l_update_dat DESC LIMIT #{number}")
    @Results(id = "activity",value =
            {
                    @Result(property = "uid",column = "uid"),
                    @Result(property = "subject",column = "subject"),
                    @Result(property = "pic",column = "pic"),
                    @Result(property = "date",column = "dat"),
                    @Result(property = "address",column = "address"),
                    @Result(property = "views",column = "views"),
                    @Result(property = "corporId",column = "corpor_id"),
                    @Result(property = "createBy",column = "create_by"),
                    @Result(property = "createDat",column = "create_dat"),
                    @Result(property = "lUpdateDat",column = "l_update_dat"),
                    @Result(property = "organ",column = "organ")
            })
    List<Activity> queryHotActivity(@Param("update") Date update, @Param("views") int views, @Param("number") int number);


    @Select("SELECT uid,subject,pic,dat,address,views,corpor_id,organ,create_dat " +
            "FROM t_com_activity " +
            "WHERE corpor_id = #{corpId} " +
            "AND create_dat<= #{date} " +
            "AND is_del = 0 AND finish = 0 " +
            "ORDER BY create_dat DESC LIMIT #{page},#{number} ")
    @Results(id = "activity2",value =
            {
                    @Result(property = "uid",column = "uid"),
                    @Result(property = "subject",column = "subject"),
                    @Result(property = "pic",column = "pic"),
                    @Result(property = "date",column = "dat"),
                    @Result(property = "address",column = "address"),
                    @Result(property = "views",column = "views"),
                    @Result(property = "corporId",column = "corpor_id"),
                    @Result(property = "createDat",column = "create_dat"),
                    @Result(property = "organ",column = "organ")
            })
    List<Activity> queryCorpActivity(@Param("corpId") String corpId, @Param("date") Date date, @Param("page") int page, @Param("number") int number);

    @Select("SELECT uid,subject,pic,dat,address,likes,corpor_id,organ,create_dat " +
            "FROM t_com_activity " +
            "WHERE is_del = 0 AND finish = 0 " +
            "ORDER BY create_dat DESC LIMIT #{number}")
    @Results(value =
            {
                    @Result(property = "uid",column = "uid"),
                    @Result(property = "subject",column = "subject"),
                    @Result(property = "pic",column = "pic"),
                    @Result(property = "date",column = "date"),
                    @Result(property = "address",column = "address"),
                    @Result(property = "likes",column = "likes"),
                    @Result(property = "corporId",column = "corpor_id"),
                    @Result(property = "like",column = "like"),
                    @Result(property = "createDat",column = "create_dat"),
                    @Result(property = "organ",column = "organ")
            })
    List<Activity> queryNewActivity(@Param("number") int number);

    @Select("SELECT uid,subject,pic,dat,address,likes,corpor_id,organ,create_dat " +
            "FROM t_com_activity " +
            "WHERE create_dat > #{date} AND " +
            "is_del = 0 AND finish = 0 " +
            "ORDER BY create_dat DESC LIMIT #{number}")
    @Results(value =
            {
                    @Result(property = "uid",column = "uid"),
                    @Result(property = "subject",column = "subject"),
                    @Result(property = "pic",column = "pic"),
                    @Result(property = "date",column = "date"),
                    @Result(property = "address",column = "address"),
                    @Result(property = "likes",column = "likes"),
                    @Result(property = "corporId",column = "corpor_id"),
                    @Result(property = "like",column = "like"),
                    @Result(property = "createDat",column = "create_dat"),
                    @Result(property = "organ",column = "organ")
            })
    List<Activity> queryNewDateActivity(@Param("date") DateTime date, @Param("number") int number);

    @Select("SELECT d.uid as uid,d.subject as subject,d.pic as pic,d.dat as dat,d.address as address,d.views as views," +
            "d.corpor_id as corpor_id,d.create_dat as create_dat,d.organ as organ " +
            "FROM t_com_activity as d INNER JOIN t_com_user_concern as u " +
            "ON u.uid=#{userId} " +
            "AND d.uid=u.aid " +
            "AND u.create_dat <= #{date} " +
            "AND u.is_del=0 " +
            "AND d.is_del=0 " +
            "AND d.finish = 0 " +
            "ORDER BY u.create_dat DESC LIMIT #{start},#{number}")
    @Results(value =
        {
                @Result(property = "uid",column = "uid"),
                @Result(property = "subject",column = "subject"),
                @Result(property = "pic",column = "pic"),
                @Result(property = "date",column = "dat"),
                @Result(property = "address",column = "address"),
                @Result(property = "views",column = "views"),
                @Result(property = "corporId",column = "corpor_id"),
                @Result(property = "createDat",column = "create_dat"),
                @Result(property = "organ",column = "organ")
        })
    List<Activity> queryConcernActivity(@Param("userId") String userId, @Param("date") Date date, @Param("start") int page, @Param("number") int number);


    @Select("SELECT d.uid as uid,d.subject as subject,d.pic as pic,d.dat as dat,d.address as address,d.views as views," +
            "d.corpor_id as corpor_id,d.create_dat as create_dat,d.organ as organ " +
            "FROM t_com_activity as d INNER JOIN t_com_user_concern as u " +
            "ON u.uid=#{userId} " +
            "AND d.uid=u.aid " +
            "AND u.create_dat > #{date} " +
            "AND u.is_del=0 " +
            "AND d.is_del=0 " +
            "AND d.finish = 0 " +
            "ORDER BY u.create_dat DESC LIMIT #{start},#{number}")
    @Results(value =
            {
                    @Result(property = "uid",column = "uid"),
                    @Result(property = "subject",column = "subject"),
                    @Result(property = "pic",column = "pic"),
                    @Result(property = "date",column = "dat"),
                    @Result(property = "address",column = "address"),
                    @Result(property = "views",column = "views"),
                    @Result(property = "corporId",column = "corpor_id"),
                    @Result(property = "createDat",column = "create_dat"),
                    @Result(property = "organ",column = "organ")
            })
    List<Activity> queryRefreshConcernActivity(@Param("userId") String userId, @Param("date") Date date, @Param("start") int page, @Param("number") int number);


}
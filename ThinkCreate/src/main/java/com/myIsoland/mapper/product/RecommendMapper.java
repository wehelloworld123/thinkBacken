package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.Recommend;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecommendMapper extends BaseMapper<Recommend> {
    @Select("SELECT id，content，likes，creator，createAvat " +
            "FROM t_pro_recom " +
            "WHERE content_id = #{no} " +
            "AND is_del = 0 " +
            "ORDER BY likes DESC " +
            "LIMIT 2 " +
            "UNION" +
            "SELECT count(id) as total " +
            "FROM t_pro_recom " +
            "WHERE content_id = #{no} " +
            "AND is_del = 0")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "content",property = "content"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "total",property = "total"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "createAvat",property = "createAvat"),
            @Result(column = "create_dat",property = "createDat")
    })
    List<Recommend> selectHotRecommend(@Param("no") String no);

    @Select("SELECT id，content，likes，creator，createAvat " +
            "FROM t_pro_recom " +
            "WHERE content_id = #{no} " +
            "AND kind = #{kind} " +
            "AND create_dat < #{date}" +
            "AND is_del = 0 " +
            "ORDER BY create_dat DESC " +
            "LIMIT #{start},30 ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "content",property = "content"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "total",property = "total"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "createAvat",property = "createAvat"),
            @Result(column = "create_dat",property = "createDat")
    })
    List<Recommend> selectRecommendByDate(@Param("no") String no,@Param("date") Date date,@Param("kind") int kind, @Param("start") int start);


    @Update("UPDATE t_pro_recom " +
            "SET likes = likes + 1,favorer = CONCAT(favorer,#{userId}) " +
            "WHERE id = #{id} " +
            "AND is_del = 0 ")
    int updateLikeSts(String userId,Long no);
}

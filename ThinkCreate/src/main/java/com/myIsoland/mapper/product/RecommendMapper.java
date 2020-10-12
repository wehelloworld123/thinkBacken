package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.Recommend;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;

@Mapper
public interface RecommendMapper extends BaseMapper<Recommend> {
    @Select("SELECT id,content,likes,creator,create_avat,create_sex,create_dat  " +
            "FROM t_pro_recom " +
            "WHERE content_id = #{no} " +
            "AND is_del = 0 " +
            "ORDER BY likes DESC " +
            "LIMIT 2 ")
  /*              "UNION" +
                        "SELECT count(id) as total " +
                        "FROM t_pro_recom " +
                        "WHERE content_id = #{no} " +
                        "AND is_del = 0"*/
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "content",property = "content"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "total",property = "total"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "create_avat",property = "createAvat"),
            @Result(column = "create_dat",property = "createDat")
    })
    List<Recommend> selectHotRecommend(@Param("no") String no);


    @Select("SELECT count(id) as count  " +
            "FROM t_pro_recom " +
            "WHERE content_id = #{no} " +
            "AND is_del = 0 " +
            "ORDER BY likes DESC " )
    List<Recommend> selectRecomCount(@Param("no") String no);

    @Select("SELECT id,content,likes,creator,create_avat,#{date} as cur_date,create_dat,instr(favorer,#{userid}) as is_like " +
            "FROM t_pro_recom " +
            "WHERE content_id = #{no} " +
            "AND typ = #{kind} " +
            "AND create_dat < #{date} " +
            "AND is_del = 0 " +
            "ORDER BY create_dat DESC " +
            "LIMIT #{start},#{limit} ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "content",property = "content"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "is_like",property = "islike"),
            @Result(column = "total",property = "total"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "create_avat",property = "createAvat"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "{id=id,date=cur_date}",property = "comments",many = @Many(select="com.myIsoland.mapper.product.CommentMapper.queryNewComment",
                    fetchType = FetchType.EAGER))
    })
    List<Recommend> selectRecommendByDate(@Param("userid")String userid,@Param("no") String no,@Param("date") Date date,@Param("kind") int kind, @Param("start") int start,@Param("limit") int limit);


    @Update("UPDATE t_pro_recom " +
            "SET likes = likes + 1,favorer = CONCAT(favorer,#{userId}),l_update_dat = now() " +
            "WHERE id = #{id} " +
            "AND is_del = 0 ")
    int updateLikeSts(@Param("userId")String userId,@Param("id")Long id);

    @Update("UPDATE t_pro_recom " +
            "SET likes = likes - 1,favorer = replace(favorer,#{userId},''),l_update_dat = now() " +
            "WHERE id = #{id} " +
            "AND is_del = 0 ")
    int delLikeSts(@Param("userId") String userId,@Param("id") Long id);
}

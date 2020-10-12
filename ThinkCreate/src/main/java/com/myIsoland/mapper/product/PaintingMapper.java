package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.Painting;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface PaintingMapper extends BaseMapper<Painting> {
    @Select("SELECT uid,seter,name,cover,topic,purpose,description,kind,is_top,create_dat " +
            "FROM t_pro_paint " +
            "WHERE kind = #{kind} AND finish = 0 AND is_del =0 " +
            "ORDER BY is_top,create_dat DESC  LIMIT #{start},20")
    @Results(id = "paint",value = {
            @Result(column = "uid",property = "uid"),
            @Result(column = "seter",property = "seter"),
            @Result(column = "name",property = "name"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "topic",property = "topic"),
            @Result(column = "purpose",property = "purpose"),
            @Result(column = "description",property = "description"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "is_top",property = "isTop"),
            @Result(column = "create_dat",property = "createDat")
    })
    List<Painting> selectHotLiters(@Param("kind") int kind, @Param("start") int start);


    @Select("SELECT uid,seter,name,cover,topic,purpose,description,kind,partner,views,is_top,create_dat " +
            "FROM t_pro_paint " +
            "WHERE kind = #{kind} " +
            "AND partner < #{partner} " +
            "OR( " +
            "partner = #{partner} " +
            "AND views < #{views} " +
            ") " +
            "AND finish = 0 " +
            "AND is_del = 0 " +
            "ORDER BY partner DESC,views DESC " +
            "LIMIT #{start},#{limit} ")
    @Results({
            @Result(column = "uid",property = "uid"),
            @Result(column = "seter",property = "seter"),
            @Result(column = "name",property = "name"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "topic",property = "topic"),
            @Result(column = "purpose",property = "purpose"),
            @Result(column = "description",property = "description"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "partner",property = "partner"),
            @Result(column = "views",property = "views"),
            @Result(column = "is_top",property = "isTop"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "part",column = "uid",one = @One(select="com.myIsoland.mapper.product.PaintingPartMapper.selectPaintingPartByUid",fetchType = FetchType.EAGER))
    })
    List<Painting> selectPaintByType(@Param("kind")String kind,@Param("partner")int partner,@Param("views")int views,@Param("start") int start,@Param("limit") int limit);


    @Select("SELECT uid,seter,name,cover,topic,purpose,kind,part,section,fin_part,fin_section,deadline,create_dat,publisher,finish " +
            "FROM t_pro_paint " +
            "WHERE uid = #{creationId} " +
            "AND is_del = 0 ")
    @Results({
            @Result(column = "uid",property = "uid"),
            @Result(column = "seter",property = "seter"),
            @Result(column = "name",property = "name"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "topic",property = "topic"),
            @Result(column = "purpose",property = "purpose"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "part",property = "parts"),
            @Result(column = "section",property = "section"),
            @Result(column = "fin_part",property = "finPart"),
            @Result(column = "fin_section",property = "finSection"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "deadline",property = "deadline"),
            @Result(column = "publisher",property = "publisher"),
            @Result(column = "finish",property = "finish")
    })

    List<Painting> selectPaintingById(String creationId);
}

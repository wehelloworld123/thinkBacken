package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.Poetry;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface PoetryMapper extends BaseMapper<Poetry> {
    @Select("SELECT uid,seter,name,cover,topic,purpose,description,kind,partner,views,create_dat " +
            "FROM t_pro_poetry " +
            "WHERE kind = #{kind} " +
            "AND partner < #{partner} " +
            "OR ( " +
            "partner = #{partner} " +
            "AND views < #{views}" +
            ") " +
            "AND finish = 0 " +
            "AND is_del = 0 " +
            "ORDER BY partner,views DESC " +
            "LIMIT #{limit} ")
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
            @Result(column = "create_dat",property = "createDat")
         /*   @Result(property = "charpt",column = "uid",one = @One(select="com.myIsoland.mapper.product.PoemSetMapper.selectSetByUid",fetchType = FetchType.EAGER))*/
    })
    List<Poetry> selectHotLiters(@Param("kind")String kind, @Param("partner")int partner, @Param("views")int views,@Param("limit") int limit);


    @Select("SELECT uid,seter,name,cover,topic,purpose,description,kind,partner,views,create_dat " +
            "FROM t_pro_poetry " +
            "WHERE partner < #{partner} " +
            "OR ( " +
            "partner = #{partner} " +
            "AND views < #{views}" +
            ") " +
            "AND finish = 0 " +
            "AND is_del = 0 " +
            "ORDER BY partner,views DESC " +
            "LIMIT #{limit} ")
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
            @Result(column = "create_dat",property = "createDat")
    })
    List<Poetry> selectInitHotLiters(@Param("partner")int partner, @Param("views")int views,@Param("limit") int limit);

    @Select("SELECT uid,seter,name,cover,topic,purpose,kind,charpter,section,fin_charpt,fin_section,deadline,create_dat,publisher,finish " +
            "FROM t_pro_poetry " +
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
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "section",property = "section"),
            @Result(column = "fin_charpt",property = "finCharpt"),
            @Result(column = "fin_section",property = "finSection"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "deadline",property = "deadline"),
            @Result(column = "publisher",property = "publisher"),
            @Result(column = "finish",property = "finish")
    })

    List<Poetry> selectPoetryById(String creationId);
}

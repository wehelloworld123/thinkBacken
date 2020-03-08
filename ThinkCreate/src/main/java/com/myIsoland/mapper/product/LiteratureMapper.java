package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.model.LiteratureModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface LiteratureMapper extends BaseMapper<Literature> {
    @Select("SELECT uid,name,cover,typ,label,introduce,kind,is_top,create_dat " +
            "FROM t_pro_liter " +
            "WHERE kind = #{kind} AND finish = 0 AND is_del =0 " +
            "ORDER BY is_top,create_dat DESC  LIMIT #{start},20")
    @Results(id = "literature",value = {
            @Result(column = "uid",property = "uid"),
            @Result(column = "name",property = "name"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "typ",property = "typ"),
            @Result(column = "label",property = "label"),
            @Result(column = "introduce",property = "introduce"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "is_top",property = "isTop"),
            @Result(column = "create_dat",property = "createDate")
    })
    List<Literature> selectLiteByType(@Param("kind") int kind,@Param("start") int start);

    @Select("SELECT uid,name,cover,typ,label,introduce,description,kind,partner,views,create_dat " +
            "FROM t_pro_liter " +
            "WHERE kind = #{kind} " +
            "AND partner < #{partner} " +
            "OR( " +
            "partner = #{partner} " +
            "AND views < #{views} " +
            ")  " +
            "AND finish = 0 " +
            "AND is_del = 0 " +
            "ORDER BY create_dat ASC " +
            "LIMIT 15 ")
    @Results({
            @Result(column = "uid",property = "uid"),
            @Result(column = "name",property = "name"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "typ",property = "typ"),
            @Result(column = "label",property = "label"),
            @Result(column = "introduce",property = "introduce"),
            @Result(column = "description",property = "description"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "partner",property = "partner"),
            @Result(column = "views",property = "views"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "charpt",column = "uid",one = @One(select="com.myIsoland.mapper.product.LiterCharptMapper.selectLiterByUid",fetchType = FetchType.EAGER))
    })
    List<Literature> selectHotLiters(@Param("kind")int kind,@Param("partner")int partner,@Param("views")int views);



    @Select("SELECT uid,name,cover,typ,label,kind,charpter,section,fin_charp,fin_section,deadline,create_dat,finish " +
            "FROM t_pro_liter " +
            "WHERE uid = #{creationId} " +
            "AND is_del = 0 ")
    @Results({
            @Result(column = "uid",property = "uid"),
            @Result(column = "name",property = "name"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "typ",property = "typ"),
            @Result(column = "label",property = "label"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "section",property = "section"),
            @Result(column = "fin_charp",property = "finCharp"),
            @Result(column = "fin_section",property = "finSection"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "deadline",property = "deadline"),
            @Result(column = "publisher",property = "publisher"),
            @Result(column = "finish",property = "finish")
    })
    List<Literature> selectLiteratureById(String creationId);
}

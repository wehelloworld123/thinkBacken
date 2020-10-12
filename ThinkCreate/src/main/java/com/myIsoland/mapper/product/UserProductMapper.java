package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.UserProduct;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserProductMapper extends BaseMapper<UserProduct> {
    @Select("SELECT A.id,A.creation_id,A.typ,A.kind,A.status,A.create_dat,B.uid,B.name,B.cover,B.charpter,B.section,B.fin_charp,B.fin_section,B.deadline,B.publisher " +
            "FROM t_pro_user_product as A INNER JOIN t_pro_liter as B " +
            "ON  A.user_id =#{userId} " +
            "AND A.create_dat <= #{date}" +
            "AND A.typ = #{type} " +
            "AND A.kind = #{kind} " +
            "AND A.creation_id = B.uid " +
            "AND A.is_del = 0 " +
            "AND B.is_del = 0 " +
            "ORDER BY A.create_dat DESC " +
            "LIMIT #{start},#{limit} ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "creation_id",property = "creationId"),
            @Result(column = "typ",property = "type"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "status",property = "status"),
            @Result(column = "create_dat",property = "createDat"),

            @Result(column = "uid",property = "uid"),
            @Result(column = "name",property = "name"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "section",property = "section"),
            @Result(column = "fin_charp",property = "finCharp"),
            @Result(column = "fin_section",property = "finSection"),
            @Result(column = "deadline",property = "deadline"),
            @Result(property = "count",column = "creation_id",one = @One(select="com.myIsoland.mapper.product.LiterContentMapper.selectConentCountByBookId",fetchType = FetchType.EAGER))

    })
    List<Map<String,Object>> selectUserLiterProductByTyp(@Param("userId") String userId, @Param("kind") int kind, @Param("type") int type, @Param("date") Date date, @Param("start") int start, @Param("limit") int limit);


    @Select("SELECT A.id,A.creation_id,A.typ,A.kind,A.status,A.create_dat,B.uid,B.name,B.cover,B.part,B.section,B.fin_part,B.fin_section,B.deadline,B.publisher " +
            "FROM t_pro_user_product as A INNER JOIN t_pro_paint as B " +
            "ON  A.user_id =#{userId} " +
            "AND A.create_dat <= #{date}" +
            "AND A.typ = 2 " +
            "AND A.kind = #{kind} " +
            "AND A.creation_id = B.uid " +
            "AND A.is_del = 0 " +
            "AND B.is_del = 0 " +
            "ORDER BY A.create_dat DESC " +
            "LIMIT #{start},#{limit} ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "creation_id",property = "creationId"),
            @Result(column = "typ",property = "type"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "status",property = "status"),
            @Result(column = "create_dat",property = "createDat"),

            @Result(column = "uid",property = "uid"),
            @Result(column = "name",property = "name"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "part",property = "charpter"),
            @Result(column = "section",property = "section"),
            @Result(column = "fin_part",property = "finCharp"),
            @Result(column = "fin_section",property = "finSection"),
            @Result(column = "deadline",property = "deadline"),
            @Result(property = "count",column = "creation_id",one = @One(select="com.myIsoland.mapper.product.PaintingContentMapper.selectConentCountByPaintId",fetchType = FetchType.EAGER))
    })
    List<Map<String,Object>> selectUserPaintProductByTyp(@Param("userId") String userId,@Param("kind") int kind,@Param("type") int type,  @Param("date") Date date, @Param("start") int start, @Param("limit") int limit);


    @Select("SELECT A.id,A.creation_id,A.typ,A.kind,A.status,A.create_dat,B.uid,B.name,B.cover,B.charpter,B.section,B.fin_charpter,B.fin_section,B.deadline,B.publisher "  +
            "FROM t_pro_user_product as A INNER JOIN t_pro_poetry as B " +
            "ON  A.user_id =#{userId} " +
            "AND A.create_dat <= #{date}" +
            "AND A.typ = #{type} " +
            "AND A.kind = #{kind} " +
            "AND A.creation_id = B.uid " +
            "AND A.is_del = 0 " +
            "AND B.is_del = 0 " +
            "ORDER BY A.create_dat DESC " +
            "LIMIT #{start},#{limit} ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "creation_id",property = "creationId"),
            @Result(column = "typ",property = "type"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "status",property = "status"),
            @Result(column = "create_dat",property = "createDat"),

            @Result(column = "uid",property = "uid"),
            @Result(column = "name",property = "name"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "section",property = "section"),
            @Result(column = "fin_charp",property = "finCharp"),
            @Result(column = "fin_section",property = "finSection"),
            @Result(column = "deadline",property = "deadline"),
            @Result(property = "count",column = "creation_id",one = @One(select="com.myIsoland.mapper.product.PoemContentMapper.selectConentCountByPoetryId",fetchType = FetchType.EAGER))
    })
    List<Map<String,Object>> selectUserPoemProductByTyp(@Param("userId") String userId,@Param("kind") int kind,@Param("type") int type,  @Param("date") Date date, @Param("start") int start, @Param("limit") int limit);



    @Select("SELECT id,creation_id,typ,kind,status create_dat " +
            "FROM t_pro_user_product as A INNER JOIN t_pro_liter as B " +
            "ON  A.user_id =#{userId} " +
            "AND A.create_dat <= #{date}" +
            "AND A.typ = 1 " +
            "AND A.kind = #{kind} " +
            "AND A.creation_id = B.uid " +
            "AND A.is_del = 0 " +
            "AND B.is_del = 0 " +

            "UNION "+
            "SELECT id,creation_id,typ,kind,status create_dat " +
            "FROM t_pro_user_product as A INNER JOIN t_pro_paint as B " +
            "ON  A.user_id =#{userId} " +
            "AND A.create_dat <= #{date}" +
            "AND A.typ = 2 " +
            "AND A.kind = #{kind} " +
            "AND A.creation_id = B.uid " +
            "AND A.is_del = 0 " +
            "AND B.is_del = 0 " +

            "UNION "+
            "SELECT id,creation_id,typ,kind,status create_dat " +
            "FROM t_pro_user_product as A INNER JOIN t_pro_poetry as B " +
            "ON  A.user_id =#{userId} " +
            "AND A.create_dat <= #{date}" +
            "AND A.typ = 3 " +
            "AND A.kind = #{kind} " +
            "AND A.creation_id = B.uid " +
            "AND A.is_del = 0 " +
            "AND B.is_del = 0 " +

            "ORDER BY create_dat DESC " +
            "LIMIT #{start},#{limit} ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "creation_id",property = "creationId"),
            @Result(column = "typ",property = "typ"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "status",property = "status"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "poetry",column = "creation_id",one = @One(select="com.myIsoland.mapper.product.PoemContentMapper.selectPoemContentById",fetchType = FetchType.EAGER))
    })
    List<UserProduct> selectUserPoemProductByStatus(@Param("userId") String userId,@Param("kind") int kind, @Param("date") Date date, @Param("start") int start, @Param("limit") int limit);
}

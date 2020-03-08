package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.UserProduct;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;

public interface UserProductMapper extends BaseMapper<UserProduct> {
    @Select("SELECT id,creation_id,typ,kind,status create_dat " +
            "FROM t_pro_user_product " +
            "WHERE userId =#{userId} " +
            "AND create_dat <= #{date}" +
            "AND typ = 1 " +
            "AND kind = #{kind} " +
            "AND is_del = 0 " +
            "ORDER BY create_dat DESC " +
            "LIMIT #{page},10 ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "creation_id",property = "creationId"),
            @Result(column = "typ",property = "typ"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "status",property = "status"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "literature",column = "creation_id",one = @One(select="com.myIsoland.mapper.product.LiteratureMapper.selectLiteratureById",fetchType = FetchType.EAGER)),
    })
    List<UserProduct> selectUserLiterProductByTyp(@Param("userId") String userId,@Param("kind") int kind, @Param("date") Date date, @Param("page") int page);


    @Select("SELECT id,creation_id,typ,kind,status create_dat " +
            "FROM t_pro_user_product " +
            "WHERE userId =#{userId} " +
            "AND create_dat <= #{date}" +
            "AND typ = 1 " +
            "AND kind = #{kind} " +
            "AND is_del = 0 " +
            "ORDER BY create_dat DESC " +
            "LIMIT #{page},10 ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "creation_id",property = "creationId"),
            @Result(column = "typ",property = "typ"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "status",property = "status"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "painting",column = "creation_id",one = @One(select="com.myIsoland.mapper.product.PoetryMapper.selectPoetryById",fetchType = FetchType.EAGER))
    })
    List<UserProduct> selectUserPaintProductByTyp(@Param("userId") String userId,@Param("kind") int kind, @Param("date") Date date, @Param("page") int page);


    @Select("SELECT id,creation_id,typ,kind,status create_dat " +
            "FROM t_pro_user_product " +
            "WHERE userId =#{userId} " +
            "AND create_dat <= #{date}" +
            "AND typ = 1 " +
            "AND kind = #{kind} " +
            "AND is_del = 0 " +
            "ORDER BY create_dat DESC " +
            "LIMIT #{page},10 ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "creation_id",property = "creationId"),
            @Result(column = "typ",property = "typ"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "status",property = "status"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "poetry",column = "creation_id",one = @One(select="com.myIsoland.mapper.product.PoemContentMapper.selectPoemContentById",fetchType = FetchType.EAGER))
    })
    List<UserProduct> selectUserPoemProductByTyp(@Param("userId") String userId,@Param("kind") int kind, @Param("date") Date date, @Param("page") int page);
}

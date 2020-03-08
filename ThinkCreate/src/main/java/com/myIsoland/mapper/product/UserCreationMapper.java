package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.UserCreation;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserCreationMapper extends BaseMapper<UserCreation> {
    @Select("SELECT id,creation_id,content_id,adopt,create_dat " +
            "FROM t_pro_user_adopt " +
            "WHERE userId = #{userId} " +
            "AND create_dat <= #{date} " +
            "AND adopt = 1" +
            "AND is_del = 0 " +
            "ORDER BY create_dat DESC " +
            "LIMIT #{page},12 ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "creation_id",property = "creationId"),
            @Result(column = "content_id",property = "contentId"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "literContent",column = "content_id",one = @One(select="com.myIsoland.mapper.product.LiterContentMapper.selectLiterContentById",fetchType = FetchType.EAGER)),
            @Result(property = "paintContent",column = "content_id",one = @One(select="com.myIsoland.mapper.product.PaintingContentMapper.selectPaintContentById",fetchType = FetchType.EAGER)),
            @Result(property = "poemContent",column = "content_id",one = @One(select="com.myIsoland.mapper.product.PoemContentMapper.selectPoemContentById",fetchType = FetchType.EAGER))
    })
    List<UserCreation> selectUserCreationByDate(@Param("userId") String userId, @Param("date") Date date,@Param("page") int page);
}

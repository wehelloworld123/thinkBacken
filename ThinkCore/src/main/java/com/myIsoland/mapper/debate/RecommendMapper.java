package com.myIsoland.mapper.debate;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.debate.Recommend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RecommendMapper extends BaseMapper<Recommend> {
    @Select("SELECT id，content，creator，create_dat create_by " +
            "FROM t_deb_recommend " +
            "WHERE parent_id = #{no} " +
            "ORDER BY create_dat DESC " +
            "LIMIT 2 ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "content",property = "content"),
            @Result(column = "creator",property = "creator"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "create_by",property = "createBy"),
    })
    List<Recommend> queryFontRecom(String no);

}
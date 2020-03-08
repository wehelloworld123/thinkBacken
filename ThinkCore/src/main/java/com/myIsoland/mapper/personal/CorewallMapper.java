package com.myIsoland.mapper.personal;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.personal.Corewall;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface CorewallMapper extends BaseMapper<Corewall> {
    @Select("SELECT uid,sign,content,mood,create_dat " +
            "FROM t_per_corewall " +
            "WHERE create_by = #{userId} " +
            "AND create_dat < #{date} " +
            "AND is_del = 0 " +
            "ORDER BY create_dat DESC " +
            "LIMIT #{start},10 ")
    @Results({
            @Result(column = "uid",property = "uid"),
            @Result(column = "content",property = "content"),
            @Result(column = "sign",property = "sign"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "mood",property = "mood"),
    })
    List<Corewall> queryCorewall(@Param("userId") String userId, @Param("date") Date date, @Param("start") int start);

}

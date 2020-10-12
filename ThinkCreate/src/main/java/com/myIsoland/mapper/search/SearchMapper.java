package com.myIsoland.mapper.search;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.search.SearchRecord;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface SearchMapper  extends BaseMapper<SearchRecord> {
    @Select("SELECT key,count,sea_type,url_id " +
            "FROM t_sea_record " +
            "WHERE count >= #{count} " +
            "OR ( " +
            "create_dat > #{startDate} " +
            "AND create_dat < #{endDate}" +
            ") " +
            "AND is_del = 0 " +
            "ORDER BY count DESC " )
    @Results({
            @Result(column = "key",property = "key"),
            @Result(column = "count",property = "count"),
            @Result(column = "sea_type",property = "seaType"),
            @Result(column = "url_id",property = "urlId")
    })
    List<SearchRecord> selectAllKeysByDateOrCount(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("count")Long count);
}

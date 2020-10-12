package com.myIsoland.mapper.file;


import com.myIsoland.enitity.file.TsysDatas;
import com.myIsoland.enitity.file.TsysDatasExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TsysDatasMapper {
    long countByExample(TsysDatasExample example);

    int deleteByExample(TsysDatasExample example);

    int deleteByPrimaryKey(String id);

    int insert(TsysDatas record);

    int insertSelective(TsysDatas record);

    List<TsysDatas> selectByExample(TsysDatasExample example);

    TsysDatas selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TsysDatas record, @Param("example") TsysDatasExample example);

    int updateByExample(@Param("record") TsysDatas record, @Param("example") TsysDatasExample example);

    int updateByPrimaryKeySelective(TsysDatas record);

    int updateByPrimaryKey(TsysDatas record);
}
package com.myIsoland.mapper.sysuser;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.sysuser.RUserlikeRel;
import org.apache.ibatis.annotations.Select;

public interface RUserlikeRelMapper extends BaseMapper<RUserlikeRel> {
    @Select("SELECT ")
    int selectUserLikeSts(String no);
}

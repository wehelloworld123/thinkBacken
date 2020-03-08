package com.myIsoland.mapper.think;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.think.Theory;
import org.apache.ibatis.annotations.Update;

public interface TheoryMapper extends BaseMapper<Theory> {
    @Update("UPDATE t_thi_theory SET views=views+1 WHERE id=#{is} AND is_del=0")
    int UpdateTheorytView(int id);


}

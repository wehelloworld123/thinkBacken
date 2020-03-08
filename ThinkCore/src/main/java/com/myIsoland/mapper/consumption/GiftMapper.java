package com.myIsoland.mapper.consumption;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.consumption.Gift;
import org.apache.ibatis.annotations.Update;

public interface GiftMapper extends BaseMapper<Gift> {
    @Update("UPDATE t_con_gift SET number = number-1 WHERE uid=#{uid} ")
    int updateNo(String uid);
}

package com.myIsoland.mapper.community;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.community.Notice;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeMapper extends BaseMapper<Notice> {
    @Select("SELECT " +
            "FROM t_com_notice n LEFT JOIN t_com_notice_user nu" +
            "ON nu.u_uid=#{userId}")
    List<Notice> selectNotice(String userId);
}

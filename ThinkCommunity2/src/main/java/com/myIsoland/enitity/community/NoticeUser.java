package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("com_notice_user")
public class NoticeUser extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.AUTO)
    /** 主键 **/
    private Integer id;

    /** 公告id **/
    private String noticeId;

    /** 用户id **/
    private String userId;

    /** 0未阅读 1 阅读 **/
    private int state;

    public NoticeUser(){
        super();
    }

    public NoticeUser(String noticeId, String userId, Integer state) {
        this.noticeId = noticeId;
        this.userId = userId;
        this.state = state;
    }
}

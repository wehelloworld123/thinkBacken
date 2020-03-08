package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_com_user_concern")
public class UserConcern extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long did;//讨论id

    private String uid;

    private String aid;//活动id
    @TableField(value = "typ")
    private int type;

    public UserConcern(){
        super();
    }

    public UserConcern(Long id,String aid, String uid, int type) {
        this.id = id;
        this.aid = aid;
        this.uid = uid;
        this.type = type;
    }

    public UserConcern(Long id,Long did, String uid, int type) {
        this.id = id;
        this.did = did;
        this.uid = uid;
        this.type = type;
    }
}

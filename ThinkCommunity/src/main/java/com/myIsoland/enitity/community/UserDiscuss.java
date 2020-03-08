package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("com_user_discuss")
public class UserDiscuss extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer d_id;

    private String u_uid;

    private int state;

    public UserDiscuss(){
        super();
    }

    public UserDiscuss(Integer d_id, String u_uid) {
        this.d_id = d_id;
        this.u_uid = u_uid;
    }
}

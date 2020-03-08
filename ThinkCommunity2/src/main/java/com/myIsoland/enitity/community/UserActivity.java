package com.myIsoland.enitity.community;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_com_user_activity")
public class UserActivity {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String a_id;//活动id

    private String u_uid;//用户id

    private int state;

    public UserActivity(){
        super();
    }

    public UserActivity(String a_id, String u_uid) {
        this.a_id = a_id;
        this.u_uid = u_uid;
    }
}

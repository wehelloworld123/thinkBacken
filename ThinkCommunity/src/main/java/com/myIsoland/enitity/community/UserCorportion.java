package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_corp")
public class UserCorportion {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String u_uid;

    private String c_uid;

    private int state;

    public UserCorportion(){
        super();
    }

    public UserCorportion(String u_uid, String c_uid) {
        this.u_uid = u_uid;
        this.c_uid = c_uid;
    }
}

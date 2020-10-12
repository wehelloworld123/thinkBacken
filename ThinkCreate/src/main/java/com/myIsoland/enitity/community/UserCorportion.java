package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.UserCorpStsType;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_com_user_corp")
public class UserCorportion extends BaseEntity implements Serializable {

    private String id;

    private String uid;

    private String cid;

    private int status;

    public UserCorportion(){
        super();
    }

    public UserCorportion(String uid, String cid, UserCorpStsType status) {
        this.status = status.getValue();
        this.uid = uid;
        this.cid = cid;
    }
}

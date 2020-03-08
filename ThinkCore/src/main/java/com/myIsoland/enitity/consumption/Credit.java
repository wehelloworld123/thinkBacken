package com.myIsoland.enitity.consumption;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.CreditType;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_per_credit")
public class Credit extends BaseEntity implements Serializable{
    @TableId(value = "flow_no",type = IdType.UUID)
    private String  flowNo;//序号

    private int kind;//类型

    private String describe;//描述

    private int eventId;//

    private int point;//分值

    private int total;//总分值

    private String accountId;//账户号

    private String username;//用户名

    public Credit() {
        super();
    }

    public Credit(CreditType kind, String describe, int eventId, int point, int total, String username) {
        this.kind = kind.getValue();
        this.describe = describe;
        this.eventId = eventId;
        this.point = point;
        this.total = total;
        this.username = username;
    }
}

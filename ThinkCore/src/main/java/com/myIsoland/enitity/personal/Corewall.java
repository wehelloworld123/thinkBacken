package com.myIsoland.enitity.personal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_per_corewall")
public class Corewall extends BaseEntity implements Serializable {
    @TableId(value = "uid",type = IdType.AUTO)
    private String uid;

    private String sign;

    private String content;

    private String mood;
}

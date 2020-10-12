package com.myIsoland.enitity.consumption;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_con_account")
public class Account extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.INPUT)
    private String uid;//

    private int literPro;

    private int paintPro;

    private int poetryPro;

    private int recomNo;

    private float adoRate;

    private int debateNo;


    private int credit;


}

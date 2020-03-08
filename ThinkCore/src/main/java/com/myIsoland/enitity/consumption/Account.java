package com.myIsoland.enitity.consumption;

import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_con_account")
public class Account extends BaseEntity implements Serializable {

    private String uid;//

    private int credit;


}

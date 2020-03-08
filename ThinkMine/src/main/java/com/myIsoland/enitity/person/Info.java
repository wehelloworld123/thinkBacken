package com.myIsoland.enitity.person;

import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_per_info")
public class Info extends BaseEntity implements Serializable {

    private Integer id;//主键

    private String alias;//别号

    private int sex;//性别

    private String belong;//派别

    private String birthday;//生日

    private String death;//死期

    private String label;//标签

    public Info(){
        super();
    }
}

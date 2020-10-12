package com.myIsoland.enitity.community;

import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("t_dis_subject")
public class Subject  extends BaseEntity implements Serializable {

    private String no;

    private String name;

    private String introduce;

    private String label;

    private Long views;

    private int authors;

    private int proNo;

    private int rank;

    public Subject(){
        super();
    }
}

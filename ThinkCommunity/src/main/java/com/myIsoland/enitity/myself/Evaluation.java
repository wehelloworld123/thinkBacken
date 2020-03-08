package com.myIsoland.enitity.myself;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.EvaluatType;

import java.io.Serializable;
@TableName("t_my_evaluat")
public class Evaluation extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String question;

    private EvaluatType type;

    private int score;

    private String title;

    private int rate;



}

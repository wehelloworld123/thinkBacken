package com.myIsoland.enitity.evaluate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_eva_report")
public class Report extends BaseEntity implements Serializable {
    @TableId(value = "no",type = IdType.INPUT)
    private String no;//编号

    private String name;//名称

    private String result;//测评结果

    private String feature;//符合特征

    private String advice;//相关建议

    private int type;//所属类型

    private String evalId;//测评卷id

    public Report() {
        super();
    }

    public Report(String no, String result, String feature, String advice, int type, String evalId) {
        this.no = no;
        this.result = result;
        this.feature = feature;
        this.advice = advice;
        this.type = type;
        this.evalId = evalId;
    }
}

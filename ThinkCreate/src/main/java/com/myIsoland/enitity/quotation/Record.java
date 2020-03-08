package com.myIsoland.enitity.quotation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("t_quo_record")
public class Record extends BaseEntity implements Serializable {
    @TableId(value="id",type= IdType.AUTO)
    private Integer id;

    private String sentence;

    private String content;

    private String analId;

}

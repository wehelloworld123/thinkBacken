package com.myIsoland.enitity.search;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@TableName("t_sea_record")
@KeySequence(value = "SEQ_ORACLE_STRING_KEY", clazz = String.class)
public class SearchRecord  extends BaseEntity implements Serializable {

    @TableId(value = "key",type = IdType.INPUT)
    private String key;
    @TableField(value = "count")
    private Long count;
    @TableField(value = "sea_type")
    private int seaType;
    @TableField(value = "url_id")
    private String urlId;
    public SearchRecord(){
        super();
    }
}

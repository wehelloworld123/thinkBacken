package com.myIsoland.enitity.personal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_per_suggest")
public class Suggestion extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String contont;

    private String username;

    private int adopt;

    public Suggestion() {
        super();
    }

    public Suggestion(String contont, String username) {
        this.contont = contont;
        this.username = username;
    }
}

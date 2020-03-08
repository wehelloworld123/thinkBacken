package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_pro_copyright")
public class Copyright extends BaseEntity implements Serializable {

    private String uid;//id

    private String proId;//作品id

    private int type;//作品类型

    private int copyright;//版权额


    private Integer contentId;//内容id
}

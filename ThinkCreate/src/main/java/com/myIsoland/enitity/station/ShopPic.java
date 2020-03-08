package com.myIsoland.enitity.station;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

@Data
@TableName("s_sta_pic")
public class ShopPic extends BaseEntity {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String url;

    private String shopNo;
}

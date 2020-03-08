package com.myIsoland.enitity.consumption;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_con_gift")
public class Gift extends BaseEntity implements Serializable {
    @TableId(value = "uid",type = IdType.UUID)
    private String uid;//礼品id

    private String name;//礼品名称

    private String image;//礼品图片

    private int number;//礼品数量

    private int credit;//礼品积分

    private int amount;//礼品金额

    private String sponsor;//赞助商

    private String address;//兑换地址

    private String describe;//描述

    private int finish;//下架

    public Gift(){
        super();
    }

    public Gift(String name, String image, int number, int credit, int amount, String sponsor, String address, String describe) {
        this.name = name;
        this.image = image;
        this.number = number;
        this.credit = credit;
        this.amount = amount;
        this.sponsor = sponsor;
        this.address = address;
        this.describe = describe;
    }
}

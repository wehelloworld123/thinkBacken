package com.myIsoland.enitity.consumption;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_con_excflow")
public class ExchangeFlow extends BaseEntity implements Serializable {
    @TableId(value = "trace_no",type = IdType.UUID)
    private String traceNo;//交易流水号

    private String giftId;//礼品id

    private String commodNo;//商品id

    private String code;//礼品二维码

    private DateTime valiDate;//有效时间

    private int finish;//完成兑换

    public ExchangeFlow() {
        super();
    }

    public ExchangeFlow(String giftId, String commodNo, String code, DateTime valiDate) {
        this.giftId = giftId;
        this.commodNo = commodNo;
        this.code = code;
        this.valiDate = valiDate;
    }
}

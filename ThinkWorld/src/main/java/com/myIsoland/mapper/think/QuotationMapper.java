package com.myIsoland.mapper.think;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.think.Quotation;
import org.apache.ibatis.annotations.Update;

public interface QuotationMapper extends BaseMapper<Quotation> {
    @Update("UPDATE t_thi_quot SET views=views+1 WHERE id=#{is} AND is_del=0")
    int UpdateQuotView(int id);


}

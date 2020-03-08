package com.myIsoland.service.quotation;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.quotation.Record;

import java.util.List;

public interface RecordService extends IService<Record>{

    List<Record> GetQuoRecord(String analId,int start);

    int SaveRecord(Record data);
}

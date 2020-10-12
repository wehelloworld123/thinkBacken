package com.myIsoland.service.search;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.search.SearchRecord;

import java.util.Date;
import java.util.List;

public interface SearchService  extends IService<SearchRecord> {

    List<SearchRecord> selectAllKeysByDateOrCount(Date startDate,Date endDate,Long count,int start,int limit);
}

package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.PoemSet;

import java.util.List;

public interface PoemSetService  extends IService<PoemSet> {
    List<PoemSet> GetPoemSetByPoetryId(String uid);

    int UpdatePoemSet(PoemSet data);

    PoemSet GetPoemSetDetail(Long id);
}

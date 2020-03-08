package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.LiterCharpt;

import java.util.List;

public interface LiterCharpService extends IService<LiterCharpt>{

    List<LiterCharpt> GetBookCharpt(String booId);

    int UpdateCharpt(LiterCharpt data);

    LiterCharpt GetCharptDetail(Long id);
}


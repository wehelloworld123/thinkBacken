package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.Poetry;
import com.myIsoland.enums.CreateKind;

import java.util.List;

public interface PoetryService  extends IService<Poetry> {
    int SavePoetry(Poetry data);

    int UpdatePoetry(Poetry data);

    List<Poetry> GetPoetryByType(CreateKind kind, int partner, int views);

    Poetry GetPoetryById(String uid);

    Poetry GetTopPoetry();
}

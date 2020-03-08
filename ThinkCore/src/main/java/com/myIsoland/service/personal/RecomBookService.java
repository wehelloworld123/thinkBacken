package com.myIsoland.service.personal;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.personal.RecomBook;

import java.util.Date;
import java.util.List;


public interface RecomBookService extends IService<RecomBook> {
    List<RecomBook> GetRecomBooks(Date date);

    int DeleteRecomBook(int id);
}

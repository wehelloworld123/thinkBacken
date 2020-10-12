package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.Poetry;
import com.myIsoland.enums.CreateEnum;
import com.myIsoland.enums.CreateKind;

import java.util.Date;
import java.util.List;

public interface PoetryService  extends IService<Poetry> {
    int SavePoetry(Poetry data);

    int UpdatePoetry(Poetry data);

    List<Poetry> GetPoetryByType(CreateEnum kind, int partner, int views,int limit);

    List<Poetry> GetPoetryByDate(CreateEnum kind, Date date, int start, int limit);

      /**
     *@Author:THINKPAD
     *@Description:获取推荐诗歌信息
     * @param date
    * @param start
    * @param limit
     *@Return:java.util.List<com.myIsoland.enitity.product.Poetry>
     *@Data:22:32 2020/3/29
            **/
    List<Poetry> GetInitNewPoetry(Date date,int limit);

    /**
     *@Author:THINKPAD
     *@Description:获取推荐诗歌信息
     * @param partner
    * @param start
    * @param limit
     *@Return:java.util.List<com.myIsoland.enitity.product.Poetry>
     *@Data:22:32 2020/3/29
     **/
    List<Poetry> GetInitHotPoetry(int partner, int views,int limit);

    Poetry GetPoetryById(String uid);

    Poetry GetTopPoetry();


    /**
     *@Author:THINKPAD
     *@Description:获取推荐诗歌
     * @param startIndex
     * @param pageSize
     *@Return:java.util.List<com.myIsoland.enitity.product.Poetry>
     *@Data:23:35 2020/6/8
     **/
    List<Poetry> GetRecomPoetry(int startIndex,int pageSize);



    /**
     * 通过关键词查询绘画信息，通过ela
     * @param keyword
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Poetry> QueryPoetryByKey(String keyword, int startIndex, int pageSize);
}

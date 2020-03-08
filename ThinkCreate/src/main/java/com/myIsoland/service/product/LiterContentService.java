package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.LiterContent;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public interface LiterContentService extends IService<LiterContent> {

    List<LiterContent> GetLiterContent(Long charpId,int start);

    /**
     *@Author:THINKPAD
     *@Description:插入创作内容
     * @param data
     *@Return:int
     *@Data:0:13 2020/1/30
     **/
    int SaveLiterContent(LiterContent data);

    int UpdateLiterContent(LiterContent data);

    int UpdateLikeSts(String userId,String no);

    int DeleteUserLiterContent(String userId,String no);

    List<LiterContent> GetUserLiterContent(String userId,int start);

    LiterContent GetTopLiterContent(String userId);

    /**
     *@Author:THINKPAD
     *@Description:跟据章节id获取热门创作内容信息
     * @param charpId
     *@Return:java.util.List<com.myIsoland.enitity.product.LiterContent>
     *@Data:23:39 2020/1/29
     **/
    List<LiterContent> GetHotContent(String userId,Long charpId);

    /**
     *@Author:THINKPAD
     *@Description:跟据日期排序获取信息
     * @param
     *@Return:java.util.List<com.myIsoland.enitity.product.LiterContent>
     *@Data:23:39 2020/1/29
     **/
    List<LiterContent> GetContentsOrderByDate(String userId,Long charpId, Date date, List<String> arr);

    /**
     *@Author:THINKPAD
     *@Description:根据创作id获取实际创作内容及热门推荐
     * @param no
     *@Return:com.myIsoland.enitity.product.LiterContent
     *@Data:21:00 2020/1/30
     **/
    LiterContent GetLiterContentDetail(String no);

    LiterContent GetAdoptContent(Long chaptId);

    LiterContent GetUserAdvanceLiterContent(String userId,int recomNo,int likes);

    List<LiterContent> GetUserLiterContentByDate(String userId,Date date,int page);
}
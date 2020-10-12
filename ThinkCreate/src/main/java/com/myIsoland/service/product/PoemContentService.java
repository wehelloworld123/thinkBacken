package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.common.config.MybatisRedisCache;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.model.ResultSet;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;

import java.util.Date;
import java.util.List;
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface PoemContentService extends IService<PoemContent> {


    int UpdatePoemContent(PoemContent data);

    int UpdateLikeSts(String userId,String no);


    /**
     *@Author:THINKPAD
     *@Description:删除用户点赞状态
     * @param userId
     * @param no
     *@Return:int
     *@Data:11:20 2020/4/12
     **/
    int DelLikeSts(String userId,String no);

    int DelUserPoemContent(String userId,String no);

    PoemContent GetPoemContentById(String no,String userId);

    PoemContent GetAdoptPoemContent(Long charpId);

    ResultSet<PoemContent> GetContentsOrderByDate(Long charptId, Date date, int start,int limit,List<String> arr);

    List<PoemContent> GetHotPoemContent(String userId,Long charptId);

    PoemContent GetUserAdvancePoemContent(String userId,int recomNo,int likes);
    /**
     *@Author:THINKPAD
     *@Description:获取用户创作诗歌
     * @param userId
     * @param date
     * @param start
     * @param limit
     *@Return:com.myIsoland.model.ResultSet<com.myIsoland.enitity.product.PoemContent>
     *@Data:22:09 2020/5/2
     **/
    ResultSet<PoemContent> GetUserPoemContentByDate(String userId, Date date, int start,int limit);


    /**
     * 获取系统推荐的诗歌创作作品
     * @param startDate
     * @param endDate
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Options(timeout = 6000)
   List<PoemContent> GetSysRecomPoemContent(Date startDate,Date endDate,int startIndex,int pageSize);


    /**
     * 获取系统推荐的用户文学作品top 1
     * @param date
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Options(timeout = 60000)
    ResultSet<PoemContent> GetFinRecomPoemContent(Date date,int startIndex,int pageSize);
}

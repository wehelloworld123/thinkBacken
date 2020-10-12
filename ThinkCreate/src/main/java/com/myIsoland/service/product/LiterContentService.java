package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.common.config.MybatisRedisCache;
import com.myIsoland.enitity.product.LiterContent;
import com.myIsoland.model.ResultSet;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;

import java.util.Collections;
import java.util.Date;
import java.util.List;
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
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

    int batchUpdateLikes(List<LiterContent> data);

    int DelLikeSts(String userId,String no);

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
    List<LiterContent> GetHotContent(Long charpId);

    /**
     *@Author:THINKPAD
     *@Description:跟据日期排序获取信息
     * @param
     *@Return:java.util.List<com.myIsoland.enitity.product.LiterContent>
     *@Data:23:39 2020/1/29
     **/
    ResultSet<LiterContent> GetContentsOrderByDate(Long charpId, Date date, int start, int limit, List<String> arr);

    /**
     *@Author:THINKPAD
     *@Description:跟据点赞数排序获取文学创作列表
     * @param
     *@Return:java.util.List<com.myIsoland.enitity.product.LiterContent>
     *@Data:23:39 2020/1/29
     **/
    ResultSet<LiterContent> GetContentsOrderByFavors(Long charpId, int likes,int start,int limit);
    /**
     *@Author:THINKPAD
     *@Description:根据创作id获取实际创作内容及热门推荐
     * @param userid,no
     *@Return:com.myIsoland.enitity.product.LiterContent
     *@Data:21:00 2020/1/30
     **/
    LiterContent GetLiterContentDetail(String userid,String no);

    /**
     * 获取已采纳用户作品
     * @param chaptId
     * @return
     */
    LiterContent GetAdoptContent(Long chaptId);

    LiterContent GetUserAdvanceLiterContent(String userId,int recomNo,int likes);


    /**
     * 根据日期获取用户创作作品
     * @param userId
     * @param date
     * @param start
     * @param limit
     * @return
     */
    ResultSet<LiterContent> GetUserLiterContentByDate(String userId,Date date,int start,int limit);


    /**
     * 获取系统推荐的用户文学作品
     * @param startDate
     * @param endDate
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Options(timeout = 6000)
    List<LiterContent> GetSysRecomLiterContent(Date startDate,Date endDate,int startIndex,int pageSize);

}

package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.common.config.MybatisRedisCache;
import com.myIsoland.enitity.product.PaintContent;
import com.myIsoland.enitity.product.PaintingPart;
import com.myIsoland.model.ResultSet;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;

import java.util.Date;
import java.util.List;
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface PaintContentService extends IService<PaintContent> {
    List<PaintContent> GetPaintContent(int partId, int start);

    int UpdatePaintContent(PaintContent data);

    /**
     * 删除用户绘画创作
     * @param userId
     * @param no
     * @return
     */
    int DelUserPaintContent(String userId,String no);

    /**
     * 更新点赞状态
     * @param userId
     * @param no
     * @return
     */
    int UpdateLikeSts(String userId,String no);
    /**
     * 删除点赞状态
     * @param userId
     * @param no
     * @return
     */
    int DelLikeSts(String userId,String no);

    PaintContent GetPaintContentById(String no,String userId);

    PaintContent GetAdoptContent(Long partId);

    ResultSet<PaintContent> GetContentsOrderByDate(String userId,Long partId, Date date, int startIndex,int pageSize, List<String> arr);

    /**
     * 根据热度获取用户创作内容
     * @param userId
     * @param partId
     * @param startIndex
     * @param pageSize
     * @return
     */
    ResultSet<PaintContent> GetContentsByLikes(String userId,Long partId, int startIndex,int pageSize);


    List<PaintContent> GetHotContent(Long partId,int startIndex,int pageSize);

    /**
     * 获取用户最优的绘画作品
     * @param userId
     * @param recomNo
     * @param likes
     * @return
     */
    PaintContent GetUserAdvancePaintContent(String userId,int recomNo,int likes);

    ResultSet<PaintContent> GetUserPaintContentByDate(String userId, Date date, int start,int limit);

    /**
     * 获取最近系统推荐的创作作品
     * @param startDate
     * @param endDate
     * @param startIndex
     * @param pages
     * @return
     */
    @Options(timeout = 6000)
    List<PaintContent> GetSysRecomPaintContent(Date startDate,Date endDate,int startIndex,int pages);




    /**
     * 获取系统推荐的用户绘画作品
     * @param date
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Options(timeout = 300000)
    ResultSet<PaintContent> GetFinRecomPaintContent(Date date,int startIndex,int pageSize);
}

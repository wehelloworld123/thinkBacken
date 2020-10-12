package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.common.config.MybatisRedisCache;
import com.myIsoland.enitity.product.Painting;
import com.myIsoland.enums.CreateEnum;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.model.ResultSet;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;

import java.util.Date;
import java.util.List;
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface PaintingService extends IService<Painting> {
    int SavePainting(Painting data);

    int UpdatePainting(Painting data);

    ResultSet<Painting> GetPaintingByType(CreateEnum kind, int partner, int views, int start,int limit);

    ResultSet<Painting> GetPaintingByDate(CreateEnum kind, Date date, int start, int limit);

    Painting GetPaintingById(String uid);

    Painting GetTopPainting();
    /**
     * 获取系统推荐的热门Ip诗歌作品
     * @param finish
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Options(timeout = 3600)
    List<Painting> GetSysRecomPainting(Date startDate,Date endDate,int finish,int startIndex,int pageSize);

    /**
     * 通过关键词查询绘画信息，通过ela
     * @param key
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Painting> QueryPaintingByKey(String key, int startIndex, int pageSize);
}

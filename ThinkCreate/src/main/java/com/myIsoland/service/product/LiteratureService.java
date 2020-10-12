package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.common.config.MybatisRedisCache;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.model.LiteratureModel;
import com.myIsoland.model.ResultSet;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface LiteratureService extends IService<Literature> {

    int SaveLiterature(Literature data);

    int UpdateLiterature(Literature data);

    /**
     * 根据类型获取作品列表
     * @param kind
     * @param partner
     * @param views
     * @param limit
     * @return
     */
    List<Literature> GetLiteratureByType(CreateKind kind,int partner,int views,int limit);
    @Options(timeout = 600)
    List<Literature> GetInitLiterByType(CreateKind kind,int partner,int views,int limit);

    Literature GetLiteratureById(String uid);
    @Options(timeout = 3600)
    List<Map<String,Object>> GetAdvanceLiterature();

    List<Literature> GetHotIpLiteratures(int partner,int views);

    List<Literature>  GetLiteratures(int start, int limit);

    /**
     * 获取系统推荐的构思作品
     * @param date
     * @return
     */
    @Options(timeout = 3600)
    Literature GetSysRecomOnThinPro(Date date);


    /**
     * 获取系统推荐的热门Ip作品
     * @param date
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Options(timeout = 3600)
    List<Literature> GetSysRecomIpPro(Date date,int startIndex,int pageSize);

    /**
     * 通过关键词查询书籍信息，通过ela
     * @param key
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Literature> QueryLiteratureByKey(String key,int startIndex,int pageSize);

    /**
     * 获取专题作品列表
     * @return
     */
    ResultSet<Map<String,Object>> GetSubjectBookList(Date date,String source,int startIndex,int pageSize);
}

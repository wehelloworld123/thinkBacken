package com.myIsoland.service.community;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.common.config.MybatisRedisCache;
import com.myIsoland.enitity.community.Corporation;
import com.myIsoland.enitity.system.TsysUser;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface CorporationService extends IService<Corporation> {

    /**
     *@Author:THINKPAD
     *@Description:获取社团信息
     * @param
     *@Return:java.util.List<com.myIsoland.enitity.community.Corporation>
     *@Data:21:50 2019/11/20
     **/
    @Options(timeout = 600)
    List<Corporation> GetCorporation();

    /**
     *@Author:THINKPAD
     *@Description:点赞社团
     * @param id
     *@Return:int
     *@Data:21:52 2019/11/20
     **/
    int SaveLikeCorp(String userId,String id);

    /**
     *@Author:THINKPAD
     *@Description:获取社团成员
     * @param corpId
     *@Return:java.util.List<com.myIsoland.enitity.system.TsysUser>
     *@Data:21:54 2019/11/20
     **/
    List<TsysUser> GetCorpUser(String corpId);

    /**
     *@Author:THINKPAD
     *@Description:获取社团信息
     * @param corpId
     *@Return:com.myIsoland.enitity.community.Corporation
     *@Data:18:41 2019/11/23
     **/
    Corporation GetCorpInfo(String corpId,String userId);
}

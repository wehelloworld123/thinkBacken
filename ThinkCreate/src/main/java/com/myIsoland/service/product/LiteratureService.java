package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.common.config.MybatisRedisCache;
import com.myIsoland.enitity.product.Literature;
import com.myIsoland.enums.CreateKind;
import com.myIsoland.model.LiteratureModel;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;

import java.util.List;
import java.util.Map;

@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface LiteratureService extends IService<Literature> {

    int SaveLiterature(Literature data);

    int UpdateLiterature(Literature data);


    List<Literature> GetLiteratureByType(CreateKind kind,int partner,int views);
    @Options(timeout = 600)
    List<Literature> GetInitLiterByType(CreateKind kind,int partner,int views);

    Literature GetLiteratureById(String uid);
    @Options(timeout = 3600)
    List<Map<String,Object>> GetAdvanceLiterature();

    List<Literature> GetHotIpLiteratures(int partner,int views);

}

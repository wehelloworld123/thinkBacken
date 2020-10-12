package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.enitity.product.PoemSet;
import com.myIsoland.enitity.product.Poetry;
import com.myIsoland.model.ResultSet;

import java.util.List;
import java.util.Map;

public interface PoemSetService  extends IService<PoemSet> {
    List<PoemSet> GetPoemSetByPoetryId(String uid,int start,int limit);



    List<PoemSet> GetPoemSetsByPoetryId(String uid,int start,int limit,Boolean containRoot);

    int UpdatePoemSet(PoemSet data);

    PoemSet GetPoemSetDetail(Long id);

    /**
     *@Author:THINKPAD
     *@Description:根据热门获取诗歌小集
     * @param startIndex
     * @param pageSize
     *@Return:com.myIsoland.model.ResultSet<com.myIsoland.enitity.product.PoemSet>
     *@Data:23:31 2020/6/8
     **/
    ResultSet<PoemSet> GetPoemSetByHotNo(int startIndex,int pageSize);

    /**
     * 通过关键词查询诗歌集信息，通过ela
     * @param keyword
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<PoemSet> QueryPoetrySetByKey(String keyword, int startIndex, int pageSize);


    /**
     *@Author:THINKPAD
     *@Description:根据小集id获取诗歌详情
     *@Return:Poetry
     *@Data:23:31 2020/6/8
     **/
    Map<String,Object> GetPoetryBySetId(Long id);

}

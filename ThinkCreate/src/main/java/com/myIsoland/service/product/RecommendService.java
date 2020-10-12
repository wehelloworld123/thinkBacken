package com.myIsoland.service.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.product.Recommend;
import com.myIsoland.enums.RecomType;
import com.myIsoland.model.ResultSet;

import javax.xml.transform.Result;
import java.util.Date;
import java.util.List;

public interface RecommendService extends IService<Recommend> {

    int SaveRecomment(Recommend data);

    int UpdateLikeSts(String userId,Long id);

    /**
     *@Author:THINKPAD
     *@Description:删除用户点赞
     * @param userId
     * @param id
     *@Return:int
     *@Data:11:58 2020/4/12
     **/
    int DelLikeSts(String userId,Long id);

    int UpdateRecomment(Recommend data);

    List<Recommend> GetRecommentByContentId(String id,int start);

    List<Recommend> GetRecommentByDate(String userId,String no, Date date, RecomType type, int start,int limit);


    ResultSet<Recommend> GetUserRecommentByDate(String userId, Date date, RecomType type, int start, int limit);
}

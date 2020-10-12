package com.myIsoland.service.community;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.community.Disscuss;
import com.myIsoland.model.ResultSet;

import java.util.Date;
import java.util.List;

public interface DisscussService extends IService<Disscuss> {


    List<Disscuss> GetHotDisscussByLike(String userId, int likes, Date update, int number);


    ResultSet<Disscuss> GetNewDateDisscuss(String userId, Date date, int start, int number,String source);


    ResultSet<Disscuss> GetConcernDis(String userId, Date date, int start, int number,String source);

    int updateLikeSts(String userId, Long id);


    int batchUpdateLikes(List<Disscuss> data);

    int delLikeSts(String userId, Long id);

    int SaveDisscuss(Disscuss data);

    /**
     *@Author:THINKPAD
     *@Description:获取帖子信息
     * @param id,userId,date
     *@Return:com.myIsoland.enitity.community.Disscuss
     *@Data:23:28 2019/11/28
     **/
    Disscuss GetDiscussInfo(Long id, String userId, Date date);

    /**
     *@Author:THINKPAD
     *@Description:根据用户id获取帖子列表
     * @param userId
     *@Return:java.util.List<com.myIsoland.enitity.community.Disscuss>
     *@Data:23:30 2019/11/28
     **/
    List<Disscuss> GetDiscussById(String userId);

    /**
     *@Author:THINKPAD
     *@Description:根据日期获取用户发布的帖子列表
     * @param userId
     * @param date
     * @param page
     *@Return:java.util.List<com.myIsoland.enitity.community.Disscuss>
     *@Data:23:52 2019/11/28
     **/
    List<Disscuss> GetUserDiscuss(String userId, Date date, int page);
}

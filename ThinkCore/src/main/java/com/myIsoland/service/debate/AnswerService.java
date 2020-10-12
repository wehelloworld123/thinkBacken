package com.myIsoland.service.debate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.debate.Answer;
import com.myIsoland.model.ResultSet;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AnswerService extends IService<Answer> {
    /**
     * 删除点赞
     * @param ansId
     * @param userId
     * @return
     */
    int delLikeSts(String ansId,String userId);
    /**
     * 点赞
     * @param ansId
     * @param userId
     * @return
     */
    int addLikeSts(String ansId,String userId);

    List<Answer> GetLastThrCapter(String topicId);

    List<Answer> GetAnswersByDate(String topicId, Date date, int start, int limit);

    List<Answer> GetAdvanceAnsByTopicId(String topicId,Date date,int likes,int recoms,int start,int limit);

    /**
     *@Author:THINKPAD
     *@Description:获取接下来回答
     * @param topicId
     * @param date
     *@Return:java.util.List<com.myIsoland.enitity.debate.Answer>
     *@Data:19:05 2020/1/26
     **/

    List<Answer> GetNextAnswer(String topicId,Date date);

    /**
     *@Author:THINKPAD
     *@Description:获取接下来热门回答
     * @param topicId
     * @param date
     *@Return:java.util.List<com.myIsoland.enitity.debate.Answer>
     *@Data:19:05 2020/1/26
     **/

    List<Answer> GetNextAdvanAns(String topicId,Date date);

    /**
     *@Author:THINKPAD
     *@Description:获取回答详情
     * @param no
     *@Return:com.myIsoland.enitity.debate.Answer
     *@Data:20:54 2020/1/26
     **/
    Answer GetAnswerById(String no,String userId);

    /**
     *@Author:THINKPAD
     *@Description:插入回答
     * @param data
     *@Return:int
     *@Data:20:56 2020/1/26
     **/
    int InsertAnswer(Answer data);

    int DeleteAns(String id);

}

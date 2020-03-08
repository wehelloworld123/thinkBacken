package com.myIsoland.service.debate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.debate.Answer;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AnswerService extends IService<Answer> {
    int AddAnsLikes(int ansId);

    List<Answer> GetLastThrCapter(String topicId);

    List<Answer> GetNewAnsById(String topicId, Date date,int start);

    List<Answer> GetAdvanceAnsById(String topicId,Date date,int start);

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
    Answer GetAnswerById(String no);

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

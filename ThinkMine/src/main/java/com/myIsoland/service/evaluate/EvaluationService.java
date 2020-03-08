package com.myIsoland.service.evaluate;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.evaluate.Evaluation;

public interface EvaluationService extends IService<Evaluation> {
    /**
     *@Author:THINKPAD
     *@Description:保存测评卷信息
     * @param data
     *@Return:int
     *@Data:16:37 2019/11/17
     **/
    int SaveEvaluation(Evaluation data);

    /**
     *@Author:THINKPAD
     *@Description:根据id获取测卷信息
     * @param id
     *@Return:com.myIsoland.enitity.evaluate.Evaluation
     *@Data:16:45 2019/11/17
     **/
    Evaluation GetEvaluation(String id);

    /**
     *@Author:THINKPAD
     *@Description:根据id删除测卷信息
     * @param uid
     *@Return:int
     *@Data:16:47 2019/11/17
     **/
    int DelEvaluation(String uid);

}

package com.myIsoland.service.think;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.think.Theory;

import java.util.List;

public interface TheoryService extends IService<Theory> {
    /**
     *@Author:THINKPAD
     *@Description:保存理论记录
     * @param data
     *@Return:int
     *@Data:18:04 2019/12/29
     **/
    int SaveTheory(Theory data);

    /**
     *@Author:THINKPAD
     *@Description:更新理论记录
     * @param data
     *@Return:int
     *@Data:18:08 2019/12/29
     **/
    int UpdateTheory(Theory data);
    /**
     *@Author:THINKPAD
     *@Description:根据更新理论记录
     * @param islock
     * @param userid
     *@Return:int
     *@Data:18:08 2019/12/29
     **/
    int UpdateTheoryByUserId(int islock,String userid);

    /**
     *@Author:THINKPAD
     *@Description:根据用户id获取理论数据
     * @param userId,lock
     *@Return:java.util.List<com.myIsoland.enitity.think.Theory>
     *@Data:18:28 2019/12/29
     **/
    List<Theory> GetTheoryByUserId(String userId,int lock);

    /**
     *@Author:THINKPAD
     *@Description:获取用户理论数据
     * @param userId
     *@Return:java.util.List<com.myIsoland.enitity.think.Theory>
     *@Data:18:28 2019/12/29
     **/
    List<Theory> GetUserTheory(String userId);

    /**
     *@Author:THINKPAD
     *@Description:更新理论浏览
     * @param id
     *@Return:int
     *@Data:21:43 2019/12/29
     **/
    int UpdateTheoryView(int id);
}

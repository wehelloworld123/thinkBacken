package com.myIsoland.service.personInfo;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.personInfo.PersonInfo;

import java.util.List;
import java.util.Map;

public interface PersonInfoService extends IService<PersonInfo> {


    /**
     *@Author:THINKPAD
     *@Description:获取用户相关信息
     * @param
     *@Return:java.util.Map<java.lang.String,java.lang.Object>
     *@Data:21:02 2019/11/30
     **/
    PersonInfo GetPersonInfo(String userId);

}

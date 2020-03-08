package com.myIsoland.service.community;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.community.Corporation;
import com.myIsoland.enitity.system.TsysUser;

import java.util.List;


public interface CorporationService extends IService<Corporation> {

    /**
     *@Author:THINKPAD
     *@Description:获取社团信息
     * @param
     *@Return:java.util.List<com.myIsoland.enitity.community.Corporation>
     *@Data:21:50 2019/11/20
     **/
    List<Corporation> GetCorporation();

    /**
     *@Author:THINKPAD
     *@Description:点赞社团
     * @param id
     *@Return:int
     *@Data:21:52 2019/11/20
     **/
    int SaveLikeCorp(String id);

    /**
     *@Author:THINKPAD
     *@Description:获取社团成员
     * @param corpId
     *@Return:java.util.List<com.myIsoland.enitity.system.TsysUser>
     *@Data:21:54 2019/11/20
     **/
    List<TsysUser> GetCorpUser(String corpId);
}

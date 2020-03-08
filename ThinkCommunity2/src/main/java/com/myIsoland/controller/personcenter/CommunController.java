package com.myIsoland.controller.personcenter;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.enitity.community.Disscuss;
import com.myIsoland.service.community.DisscussService;
import com.myIsoland.shiro.util.ShiroUtils;
import com.myIsoland.util.DateUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "用户界面交流贴接口")
@RequestMapping("Communicate")
public class CommunController {
    @Autowired
    private DisscussService disscussService;

    /**
     *@Author:THINKPAD
     *@Description:根据交流贴发布日期获取用户交流贴
     * @param date
     * @param lastId
     *@Return:java.lang.Object
     *@Data:22:16 2019/11/28
     **/
    @GetMapping("/readUserCommunities")
    public Object ReadUserCommunities(String date,int page){
        String userId = ShiroUtils.getUserId();
        List<Disscuss> data = disscussService.GetUserDiscuss(userId, DateUtils.parseDate(date),page);
        return AjaxResult.success(data);
    }
}

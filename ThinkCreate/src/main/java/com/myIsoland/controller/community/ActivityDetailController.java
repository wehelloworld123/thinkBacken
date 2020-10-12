package com.myIsoland.controller.community;

import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.enitity.community.UserConcern;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.service.community.UserConcernService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动详情controller
 * @author xuyong
 * @date: 2019年11月20日 下午4:23:50
 */
@RestController
@Api(value = "活动详情信息")
@RequestMapping("ActivityDetail")
public class ActivityDetailController {
    @Autowired
    private UserConcernService userConcernService;
    @Autowired
    private RedisCacheService redisCacheService;

    @PostMapping("/createCon")
    public Object CreateCon(String id){

        UserConcern data = new UserConcern();
        data.setAid(id);
        data.setUid(ShiroUtils.getUserId());
        data.setType(2);
        userConcernService.saveOrUpdate(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }

    @PostMapping("/delConcern")
    public Object DelConcern(String id){
        int i = userConcernService.DelUserActivity(id, ShiroUtils.getUserId());
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}

package com.myIsoland.controller.system;


import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.Recommend;
import com.myIsoland.enitity.product.UserCreation;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.RecomType;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.RecommendService;
import com.myIsoland.service.product.UserCreationService;
import com.myIsoland.service.system.TsysUserService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "用户信息背景")
@RequestMapping("UserInfo")
public class UserInfoController {
    @Autowired
    private RecommendService recommendService;

    @Autowired
    private UserCreationService userCreationService;

    @Autowired
    private TsysUserService tsysUserService;


    @GetMapping("/readUserBgInfo")
    public Object ReadUserBgInfo(String userId){
        userId = ProjectConstant.USERPREFIX + userId;
        TsysUser userinfo = tsysUserService.getById(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userinfo.getUsername());
        map.put("nickname",userinfo.getNickname());
        map.put("avatar",userinfo.getAvatar());
        map.put("sex",userinfo.getSex());
        map.put("description",userinfo.getDescription());
        return AjaxResult.success(map);
    }
    @GetMapping("/readUserAdoptCreations")
    public Object ReadUserAdoptCreations(String userid,String date,int start,int limit){
        ResultSet<UserCreation> data = userCreationService.GetUserAdoptContent(userid,DateUtils.parseDate(date),start,limit);
        return AjaxResult.success(data);
    }

    @GetMapping("/readUserRecommend")
    public Object ReadUserRecommend(String userId,int type,String date,int start,int limit){
        userId = ProjectConstant.USERPREFIX+userId;
        ResultSet<Recommend> resultSet = recommendService.GetUserRecommentByDate(userId, DateUtils.parseDate(date), RecomType.valueOf(type),start,limit);

        return AjaxResult.success(resultSet);
    }
}

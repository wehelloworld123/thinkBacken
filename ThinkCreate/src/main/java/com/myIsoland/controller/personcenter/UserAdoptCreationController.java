package com.myIsoland.controller.personcenter;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.enitity.product.UserCreation;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.UserCreationService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "采纳用户创作页面接口")
@RequestMapping("UserAdoptCreation")
public class UserAdoptCreationController {
    @Autowired
    private UserCreationService userCreationService;

    @GetMapping("/readUserAdoptCreations")
    public Object ReadUserAdoptCreations(String date,int start,int limit){
       ResultSet<UserCreation> data = userCreationService.GetUserAdoptContent(ShiroUtils.getUserId(),DateUtils.parseDate(date),start,limit);
       return AjaxResult.success(data);
    }
}

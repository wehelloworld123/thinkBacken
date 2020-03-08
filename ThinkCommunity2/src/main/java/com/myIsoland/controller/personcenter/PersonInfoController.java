package com.myIsoland.controller.personcenter;

import cn.hutool.core.date.DateUtil;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.personInfo.PersonInfo;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.service.community.CommentService;
import com.myIsoland.service.personInfo.PersonInfoService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人信息controller
 * @author xuyong
 * @date: 2019年11月20日 下午4:23:50
 */
@RestController
@Api(value = "个人信息")
@RequestMapping("PersonInfo")
public class PersonInfoController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PersonInfoService personInfoService;

    /**
     *@Author:THINKPAD
     *@Description:初始化论坛用户信息界面
     *@Return:java.lang.Object
     *@Data:22:15 2019/11/28
     **/
    @GetMapping("/readInitPersonInfo")
    public Object ReadInitPersonInfo(){
        Map<String,Object> map = new HashMap<>();
        String userId = ShiroUtils.getUserId();
        PersonInfo personInfo = personInfoService.GetPersonInfo(userId);
        map.put("personInfo",personInfo);
        return AjaxResult.success(map);

    }


}
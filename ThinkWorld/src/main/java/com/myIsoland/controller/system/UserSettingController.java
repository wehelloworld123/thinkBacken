package com.myIsoland.controller.system;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.service.system.TsysUserService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户设置")
@RequestMapping("UserSetting")
public class UserSettingController {
    @Autowired
    private TsysUserService tsysUserService;

    @PostMapping("/modifyUserThinkSts")
    public Object ModifyUserThinkSts(boolean think){

        TsysUser data = new TsysUser();
        data.setId(ShiroUtils.getUserId());
        if(think){
            data.setThink(1);
        }else {
            data.setThink(0);
        }
        tsysUserService.updateById(data);

        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());

    }
}

package com.myIsoland.controller.personal;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.enitity.personal.Suggestion;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.service.personal.SuggestService;
import com.myIsoland.service.system.TsysUserService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api(value = "用户中心墙界面")
@RequestMapping("Personal")
public class PersonalController {
    @Autowired
    private SuggestService suggestService;
    @Autowired
    private TsysUserService tsysUserService;

    /**
     *@Author:THINKPAD
     *@Description:创建用户建议评论
     * @param content
     *@Return:java.lang.Object
     *@Data:0:39 2020/1/28
     **/
    @PostMapping("/createSuggestion")
    public Object CreateSuggestion(String content){
        Suggestion data = new Suggestion();
        data.setContont(content);
        data.setUsername(ShiroUtils.getLoginName());
        suggestService.save(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());

    }

    @PostMapping("modifyUserAvatar")
    public Object ModifyUserAvatar(String nickname, int sex, String email, String description, @RequestParam(value = "username",required = false) String username, @RequestParam(value = "file",required = false) MultipartFile imagefile){
        TsysUser data = new TsysUser();
        data.setDescription(description);
        data.setNickname(nickname);
        if(username!=null&&!"".equals(username)){
            data.setUsername(username);
        }
        data.setSex(sex);
        data.setId(ShiroUtils.getUserId());
        tsysUserService.updateById(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }

    @PostMapping("modifyUserInfo")
    public Object ModifyUserInfor(String nickname, int sex, String email, String description, @RequestParam(value = "username",required = false) String username){
        TsysUser data = new TsysUser();
        data.setDescription(description);
        data.setNickname(nickname);
        if(username!=null&&!"".equals(username)){
            data.setUsername(username);
        }
        data.setSex(sex);
        data.setId(ShiroUtils.getUserId());
        tsysUserService.updateById(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}

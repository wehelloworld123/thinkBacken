package com.myIsoland.controller.personcenter;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.service.community.CommentService;
import com.myIsoland.service.community.DisscussService;
import com.myIsoland.shiro.util.ShiroUtils;
import com.myIsoland.util.DateUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户界面用户回复接口")
@RequestMapping("ReplyUser")
public class ReplyUserController {
    @Autowired
    private DisscussService disscussService;
    @Autowired
    private CommentService commentService;

    /**
     *@Author:THINKPAD
     *@Description:根据交流贴发布日期获取用户交流贴
     * @param date
     * @param lastId
     *@Return:java.lang.Object
     *@Data:22:16 2019/11/28
     **/
    @GetMapping("/readUserReplies")
    public Object ReadUserReplies(String date,int page){
        return AjaxResult.success(
                commentService.GetReplyCommentByDate(ShiroUtils.getUserId(),page,DateUtils.parseDate(date)));
    }
}

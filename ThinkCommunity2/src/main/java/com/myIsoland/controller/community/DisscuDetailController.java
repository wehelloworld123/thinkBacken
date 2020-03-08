package com.myIsoland.controller.community;

import cn.hutool.core.date.DateUtil;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.enitity.community.Comment;
import com.myIsoland.enitity.community.UserConcern;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.SexType;
import com.myIsoland.service.community.CommentService;
import com.myIsoland.service.community.DisscussService;
import com.myIsoland.service.community.UserConcernService;
import com.myIsoland.shiro.util.ShiroUtils;
import com.myIsoland.util.DateUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 帖子详情controller
 * @author xuyong
 * @date: 2019年11月20日 下午4:23:50
 */
@RestController
@Api(value = "帖子详情信息")
@RequestMapping("DisscuDetail")
public class DisscuDetailController {
    @Autowired
    private UserConcernService userConcernService;
    @Autowired
    private DisscussService disscussService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/createCon")
    public Object CreateCon(Long id){
        UserConcern data = new UserConcern();
        data.setDid(id);
        data.setUid(ShiroUtils.getUserId());
        data.setType(2);
        userConcernService.saveOrUpdate(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }

    @PostMapping("/delConcern")
    public Object DelConcern(Long id){
        int i = userConcernService.DelUserDiscuss(id,ShiroUtils.getUserId());
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }


    /**
     *@Author:THINKPAD
     *@Description:根据交流贴界面获取帖子评论信息
     * @param id
     * @param date
     * @param page
     *@Return:java.lang.Object
     *@Data:15:55 2020/2/9
     **/
    @GetMapping("/readCommentsOrderDat")
    public Object ReadCommentsOrderDat(Long id,String date,int page,int type){
        Map<String,Object> data = new  HashMap<>();
        String userId = ShiroUtils.getUserId();
        if(type==0) {
            Comment comment = commentService.GetHotComment(userId, id);
            data.put("comment",comment);
        }
        List<Comment> comments = commentService.GetCommentByDate(userId,id, DateUtil.parseDateTime(date),page,40);
        data.put("comments",comments);
        return AjaxResult.success(data);
    }

    /**
     *@Author:THINKPAD
     *@Description:阅读接下来的评论
     * @param id
     * @param date
     * @param page
     *@Return:java.lang.Object
     *@Data:15:57 2020/2/9
     **/
    @GetMapping("/readNextComments")
    public Object ReadNextComments(Long id,String date,int page){
        return AjaxResult.success(
                commentService.GetCommentByDate(ShiroUtils.getUserId(),id,DateUtil.parseDate(date),page,20));
    }
    /**
     *@Author:THINKPAD
     *@Description:创建评论
     * @param content
     * @param rootId
     * @param replyId
     * @param replyName
     * @param summary
     *@Return:java.lang.Object
     *@Data:15:52 2020/2/9
     **/
    @PostMapping("/createComment")
    public Object CreateComment(String content,Long rootId,String replyId,String replyName,String summary){
        TsysUser userEntity = ShiroUtils.getUser();
        Comment data = new Comment(content, rootId, replyId, replyName,userEntity.getUsername(),
                SexType.valueOf(userEntity.getSex()), userEntity.getAvatar(),summary);
        int i = commentService.SaveComment(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
    /**
     *@Author:THINKPAD
     *@Description:在用户中心通过帖子id或者回复获取交流贴具体信息和评论
     * @param id
     * @param date
     *@Return:java.lang.Object
     *@Data:15:42 2020/2/9
     **/
    @GetMapping("/readDisAndCommentsByUser")
    public Object ReadDisAndCommentsByUser(Long id,String date){
        return AjaxResult.success(disscussService.GetDiscussInfo(id,ShiroUtils.getUserId(), DateUtils.parseDate(date)));
    }
}

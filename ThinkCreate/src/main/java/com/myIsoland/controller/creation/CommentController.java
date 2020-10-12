package com.myIsoland.controller.creation;

import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.DateUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.Comment;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.model.ResultSet;
import com.myIsoland.service.product.CommentService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@Api(value = "推荐评论界面")
@RequestMapping("Comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/readComments")
    public Object ReadComments(String id,String date,int start,int limit){
        System.out.println(id);
        System.out.println(date);
        System.out.println(start);
        System.out.println(limit);
        ResultSet<Comment> data = null;
        try{
            if(isNumeric(id)){
                data = commentService.GetCommentByDate(new Long(id), DateUtils.parseDate(date),start,limit);
                data.setList(CaculateUtils.deleteComPrefix(data.getList()));
                return AjaxResult.success(data);
            }else{
                return AjaxResult.error(CodeEnum.ILLEGAL_STRING.getCode(),CodeEnum.ILLEGAL_STRING.getMessage(),null);
            }
        }catch (NumberFormatException e){
            return AjaxResult.error(CodeEnum.ILLEGAL_STRING.getCode(),CodeEnum.ILLEGAL_STRING.getMessage(),null);
        }
    }



    @PostMapping("/createComment")
    public Object CreateComment(Long rootId,String replyId,String content,String replyName){
        Comment data = new Comment();
        TsysUser tsysUser = ShiroUtils.getUser();
        data.setReplyId(ProjectConstant.USERPREFIX+replyId);
        data.setContent(content);
        data.setReplyName(replyName);
        data.setRootId(rootId);
        data.setCreator(tsysUser.getUsername());
        data.setCreatorAvat(tsysUser.getAvatar());
        data.setCreatorSex(tsysUser.getSex());
        data.setCreateBy(tsysUser.getId());
        commentService.save(data);
        return AjaxResult.success(data);
    }

    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if(!isNum.matches()) {
            return false;
        }
        return true;
    }
}

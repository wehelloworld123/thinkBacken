package com.myIsoland.controller.community;

import cn.hutool.core.date.DateUtil;
import com.myIsoland.common.component.RedisCacheService;
import com.myIsoland.common.domain.AjaxResult;
import com.myIsoland.constant.THINKConstant;
import com.myIsoland.enitity.community.Comment;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.enums.CodeEnum;
import com.myIsoland.enums.SexType;
import com.myIsoland.service.community.CommentService;
import com.myIsoland.shiro.util.ShiroUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 评论controller
 * @author xuyong
 * @date: 2019年11月20日 下午4:23:50
 */
@RestController
@Api(value = "评论信息")
@RequestMapping("Comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private RedisCacheService redisCacheService;

    @PostMapping("/createComment")
    public Object CreateComment(String content,Long rootId,String replyId,String replyName,String summary){
        TsysUser userEntity = ShiroUtils.getUser();
        Comment data = new Comment(content, rootId, replyId, replyName,userEntity.getUsername(),
                SexType.valueOf(userEntity.getSex()), userEntity.getAvatar(),summary);
        commentService.save(data);
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }

    @PostMapping("/modifyLikes")
    public Object ModifyLikes(Long id,int type){
        String userId = ShiroUtils.getUserId();
        Boolean b = redisCacheService.hashFieldExist("f_like_com_"+id,userId);
        if (type==1){
            if (b){
                redisCacheService.setHashValue("f_like_com_"+id,userId,"1");
            }
        }else if(type==0){
            if(b){
                if(redisCacheService.delHashField("f_like_com_"+id,userId).equals(0)){
                    return AjaxResult.error(CodeEnum.REDIS_EXCEPTION.getCode(),CodeEnum.REDIS_EXCEPTION.getMessage(),null);
                }
            }else {
                commentService.delLikeSts(userId,id);
            }
        }
        return AjaxResult.success(CodeEnum.SQL_SUCCESS.getMessage());
    }
}

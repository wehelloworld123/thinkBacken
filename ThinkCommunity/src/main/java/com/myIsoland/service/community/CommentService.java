package com.myIsoland.service.community;


import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.community.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {

    /**
     *@Author:THINKPAD
     *@Description:根据帖子id获取评论内容
     * @param rootId
     *@Return:java.util.List<com.myIsoland.enitity.community.Comment>
     *@Data:21:18 2019/11/20
     **/
    List<Comment> GetComment(Integer rootId,int page);

    List<Comment> GetUserComment(String userId);

    int SaveComment(Comment data);
}

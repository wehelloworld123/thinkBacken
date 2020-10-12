package com.myIsoland.service.community;


import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.common.config.MybatisRedisCache;
import com.myIsoland.enitity.community.Comment;
import com.myIsoland.model.ResultSet;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Options;

import java.util.Date;
import java.util.List;
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface CommentService extends IService<Comment> {

    /**
     *@Author:THINKPAD
     *@Description:根据帖子id获取评论内容
     * @param rootId,number
     *@Return:java.util.List<com.myIsoland.enitity.community.Comment>
     *@Data:21:18 2019/11/20
     **/
    @Options(timeout = 600)
    List<Comment> GetComments(Long rootId,Date date,int userId);

    /**
     *@Author:THINKPAD
     *@Description:根据帖子id和日期时间获取评论内容
     * @param rootId,date,number
     *@Return:java.util.List<com.myIsoland.enitity.community.Comment>
     *@Data:21:18 2019/11/20
     **/
    @Options(useCache = false)
    ResultSet<Comment> GetCommentByDate(String userId,Long rootId, Date date,int start,int number);
    /**
    *@Author:THINKPAD
     *@Description:获取用户评论
     * @param userId
     *@Return:java.util.List<com.myIsoland.enitity.community.Comment>
     *@Data:22:19 2019/11/28
            **/
    @Options(useCache = false)
    List<Comment> GetUserComment(String userId);

    @Options(timeout = 300)
    ResultSet<Comment> GetHotComment(String userId, Long rootId,int startIndex,int pageSize);

    int SaveComment(Comment data);


    /**
     *@Author:THINKPAD
     *@Description:根据日期获取回复评论
     * @param  userId,lastId,date
     *@Return:java.util.List<com.myIsoland.enitity.community.Comment>
     *@Data:21:33 2019/11/28
     **/
    @Options(useCache = false)
    List<Comment> GetReplyCommentByDate(String userId,int page,Date date);

    int updateLikeSts(String userId,Long id);

    int delLikeSts(String userId,Long id);
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    int batchUpdateLikes(List<Comment> data);

}

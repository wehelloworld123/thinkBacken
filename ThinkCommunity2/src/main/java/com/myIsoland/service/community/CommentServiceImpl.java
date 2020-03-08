package com.myIsoland.service.community;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.myIsoland.enitity.community.Comment;
import com.myIsoland.mapper.community.CommentMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper,Comment> implements CommentService {

    @Override
    public List<Comment> GetComments(Long rootId,Date date,int userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",rootId);
        map.put("userid",userId);
        map.put("date",date);
        return this.baseMapper.queryComment(map);
    }

    @Override
    public List<Comment> GetCommentByDate(String userId,Long rootId, Date date, int start,int number) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        String sql = "LIMIT "+start+","+number;
        wrapper.select("id","content","root_id","reply_id","reply_name","creator","creator_sex","creator_avat","create_by","create_dat","instr(favorer,"+userId+") as isLike")
                .lambda().eq(Comment::getRootId,rootId)
                .le(Comment::getCreateDat,date)
                .eq(Comment::getIsDel,0)
                .orderByDesc(Comment::getCreateDat)
                .last(sql);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Comment> GetUserComment(String userId) {
        IPage<Comment> commentIPage=new Page<>(1,10);
        commentIPage= this.baseMapper.selectPage(commentIPage,new QueryWrapper<Comment>().eq("create_by",userId).eq("is_del",0).orderByDesc("create_dat"));
        List<Comment> list=commentIPage.getRecords();
        return  list;
    }

    @Override
    public Comment GetHotComment(String userId,Long rootId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.select("id","content","root_id","reply_id","reply_name","creator","creator_sex","creator_avat","create_by","create_dat","instr(favorer,"+userId+") as isLike")
                .lambda()
                .eq(Comment::getRootId,rootId)
                .ge(Comment::getLikes,100)
                .eq(Comment::getReplyId,null)
                .eq(Comment::getIsDel,0)
                .orderByDesc(Comment::getCreateDat)
                .last("limit 1");
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public int SaveComment(Comment data) {
        return this.baseMapper.insert(data);
    }



    @Override
    public List<Comment> GetReplyCommentByDate(String userId,int page,Date date) {
/*        page = page*20;
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getReplyId,userId)
                .or().eq(Comment::getCreateBy,userId)
               .le(Comment::getCreateDat,date)
               .eq(Comment::getIsDel,0)
               .orderByDesc(Comment::getCreateDat)
               .last("limit "+page+",20");*/
        page = page * 20;
        return this.baseMapper.queryUserComment(userId,date,page,20);
    }

    @Override
    public int updateLikeSts(String userId, Long id) {
        return this.baseMapper.updateLikeSts(userId, id);
    }

    @Override
    public int delLikeSts(String userId, Long id) {
        return this.baseMapper.delLikeSts(userId,id);
    }

    @Override
    public int batchUpdateLikes(List<Comment> data) {
        return this.baseMapper.batchUpdateLikeSts(data);
    }
}

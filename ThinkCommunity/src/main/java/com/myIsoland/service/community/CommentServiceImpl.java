package com.myIsoland.service.community;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.community.Comment;
import com.myIsoland.mapper.community.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl extends ServiceImpl<CommentMapper,Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> GetComment(Integer rootId,int page) {
        return commentMapper.queryComment(rootId, page);
    }

    @Override
    public List<Comment> GetUserComment(String userId) {
        IPage<Comment> commentIPage=new Page<>(1,10);
        commentIPage= this.baseMapper.selectPage(commentIPage,new QueryWrapper<Comment>().eq("create_by",userId).eq("is_del",0).orderByDesc("create_dat"));
        List<Comment> list=commentIPage.getRecords();
        return  list;
    }

    @Override
    public int SaveComment(Comment data) {
        return commentMapper.insert(data);
    }
}

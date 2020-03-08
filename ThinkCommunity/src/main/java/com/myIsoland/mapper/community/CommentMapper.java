package com.myIsoland.mapper.community;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.community.Comment;
import com.myIsoland.enums.SexType;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    @Select("SELECT id ,content,root_id,parent_id,creator,creator_sex,creator_avat,create_by,create_dat " +
            "FROM t_com_comment " +
            "WHERE root_id = #{rootId} AND is_del=0 " +
            "LIMIT #{page}，20")
    @Results(id = "comment",value =
            {
                    @Result(property = "id",column = "id"),
                    @Result(property = "content",column = "content"),
                    @Result(property = "rootId",column = "root_id"),
                    @Result(property = "ParentId",column = "parentId"),
                    @Result(property = "creator",column = "creator"),
                    @Result(property = "creatorSex",column = "creator_sex"),
                    @Result(property = "creatorAvat",column = "creator_avat"),
                    @Result(property = "createBy",column = "create_by"),
                    @Result(property = "createDat",column = "create_dat")

    })
    List<Comment> queryComment(Integer rootId,int page);
}

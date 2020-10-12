package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.PoemContent;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

public interface PoemContentMapper extends BaseMapper<PoemContent> {


    //获取用户在该诗歌创作
    @Select("SELECT found_rows() " +
            "FROM t_pro_poem_content " +
            "WHERE poetry_id = #{content_id} " +
            "AND is_del = 0 " )
    int selectConentCountByPoetryId(String content_id);
    /**
     *@Author:THINKPAD
     *@Description:根据诗歌id获取采纳作品
     * @param id
     *@Return:com.myIsoland.enitity.product.PoemContent
     *@Data:20:21 2020/1/31
     **/
    @Select("SELECT no,brand,title,content,likes,recom_no,adopt,create_by,create_dat " +
            "FROM t_pro_poem_content " +
            "WHERE charp_id = #{id} " +
            "AND adopt = 1 " +
            "AND is_del = 0 " +
            "LIMIT 1 ")
    @Results(id = "content",value = {
            @Result(column = "no",property = "no"),
            @Result(column = "brand",property = "brand"),
            @Result(column = "title",property = "title"),
            @Result(column = "content",property = "content"),
            @Result(column = "charp_id",property = "charpId"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "poetry_name",property = "poetryName"),
            @Result(column = "sec_name",property = "secName"),
            @Result(column = "charp_name",property = "charpName"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "creators",property = "creators")
    })
    PoemContent selectAdoptConent(Long id);

    @Select("SELECT no,brand,title,content,charp_id,poetry_id,poetry_name,charp_name,sec_name,likes,instr(favorer,#{userid}) as is_like," +
            "recom_no,adopt,create_by,create_dat " +
            "FROM t_pro_poem_content " +
            "WHERE no = #{no} " +
            "AND is_del = 0 " )

    @Results(value = {
            @Result(column = "no",property = "no"),
            @Result(column = "brand",property = "brand"),
            @Result(column = "title",property = "title"),
            @Result(column = "content",property = "content"),
            @Result(column = "charp_id",property = "charpId"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "poetry_name",property = "poetryName"),
            @Result(column = "sec_name",property = "secName"),
            @Result(column = "charp_name",property = "charpName"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "is_like",property = "islike"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "create_dat",property = "createDat")
    })
    PoemContent selectPoemContentById(String no);

    @Select("SELECT no,brand,title,content,charp_id,poetry_id,poetry_name,charp_name,sec_name,likes,instr(favorer,#{userid}) as is_like," +
            "recom_no,adopt,create_by,create_dat " +
            "FROM t_pro_poem_content " +
            "WHERE no = #{no} " +
            "AND is_del = 0 " )

    @Results(value = {
            @Result(column = "no",property = "no"),
            @Result(column = "brand",property = "brand"),
            @Result(column = "title",property = "title"),
            @Result(column = "content",property = "content"),
            @Result(column = "charp_id",property = "charpId"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "poetry_name",property = "poetryName"),
            @Result(column = "sec_name",property = "secName"),
            @Result(column = "charp_name",property = "charpName"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "is_like",property = "islike"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "{id=create_by}",property = "userInfo",one = @One(select="com.myIsoland.mapper.system.TsysUserMapper.queryUserInfoById",
                    fetchType = FetchType.EAGER))
    })
    PoemContent selectPoemContentById(@Param("no") String no,@Param("userid") String userid);


    @Update("UPDATE t_pro_poem_content " +
            "SET likes = likes + 1,favorer = CONCAT(favorer,#{userId}),l_update_dat = now()  " +
            "WHERE no = #{no} " +
            "AND is_del = 0 ")
    int updateLikeSts(@Param("userId") String userId,@Param("no") String no);


    @Update("UPDATE t_pro_poem_content " +
            "SET likes = likes - 1,favorer = replace(favorer,#{userId},''),l_update_dat = now() " +
            "WHERE no = #{no} " +
            "AND is_del = 0 ")
    int delLikeSts(@Param("userId") String userId,@Param("no")String no);
}

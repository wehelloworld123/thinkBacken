package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.LiterContent;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.sql.Ref;
import java.util.Date;
import java.util.List;

public interface LiterContentMapper extends BaseMapper<LiterContent>{

    @Select("SELECT c.no,c.content,c.detail,c.file,c.charp_id,c.likes,c.recom_no,c.view,c.adopt,c.create_by,u.nickname,u.avatar " +
            "FROM t_pro_content as c LEFT JOIN t_sys_user as u " +
            "ON c.charp_id = #{charpId} AND c.id_del = 0 " +
            "WHERE c.create_by = u.id AND u.is_del = 0 " +
            "ORDER BY c.adopt, c.create_dat DESC  LIMIT #{start},20")
    @Results(id = "literContent",value = {
            @Result(column = "no",property = "no"),
            @Result(column = "summary",property = "summary"),
            @Result(column = "content",property = "content"),
            @Result(column = "file",property = "file"),
            @Result(column = "charp_id",property = "charpId"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "view",property = "view"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "nickname",property = "creator"),
            @Result(column = "avatar",property = "createAvat")
    })
    List<LiterContent> selectLiterContent(@Param("charpId") Long charpId,@Param("start") int start);
    @Select("SELECT c.no as no,c.content as content,c.detail as detail,c.file as file, " +
            "c.charp_id as charp_id,l.name as book_name,c.charp_name as charp_name,c.sec_name as secName, " +
            "c.likes as likes,c.recom_no as recom_no,c.view as view,c.adopt as adopt,c.create_by as create_by " +
            "FROM t_pro_liter as l " +
            "RIGHT JOIN t_pro_content as c " +
            "ON c.create_by=#{userId} AND l.uid=c.book_id AND l.is_del=0 " +
            "WHERE c.is_del=0 " +
            "ORDER BY c.create_dat DESC  LIMIT #{start},20")
    @Results(value = {
            @Result(column = "no",property = "no"),
            @Result(column = "summary",property = "summary"),
            @Result(column = "content",property = "content"),
            @Result(column = "file",property = "file"),
            @Result(column = "charp_id",property = "charpId"),
            @Result(column = "book_name",property = "bookName"),
            @Result(column = "charp_name",property = "charpName"),
            @Result(column = "sec_name",property = "secName"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "view",property = "view"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
    })
    List<LiterContent> selectUserliterCont(@Param("userId") String userId,@Param("start")int start);


    @Select("SELECT c.no as no,c.content as content,c.detail as detail,c.file as file, " +
            "c.charp_id as charp_id,l.name as book_name,c.charp_name as charp_name,c.sec_name as secName, " +
            "c.likes as likes,c.recom_no as recom_no,c.view as view,c.adopt as adopt,c.create_by as create_by " +
            "FROM t_pro_liter as l " +
            "RIGHT JOIN t_pro_content as c " +
            "ON c.create_by=#{userId} AND l.uid=c.book_id AND l.is_del=0 " +
            "WHERE c.is_del=0 " +
            "ORDER BY c.recom_no,c.likes DESC  LIMIT 1")

    LiterContent selectTopliterCont(@Param("userId") String userId);

    @Select("SELECT no,title,summary,likes,recom_no,adopt,create_by,create_dat " +
            "FROM t_pro_content " +
            "WHERE charp_id = #{id} " +
            "AND adopt = 1 " +
            "AND is_del = 0 " +
            "LIMIT 1" +
            "UNION" +
            "SELECT count(no) as creators " +
            "FROM t_pro_content " +
            "WHERE charp_id = #{id} " +
            "AND is_del = 0 ")
    @Results(value = {
            @Result(column = "no",property = "no"),
            @Result(column = "title",property = "title"),
            @Result(column = "summary",property = "summary"),
            @Result(column = "content",property = "content"),
            @Result(column = "file",property = "file"),
            @Result(column = "charp_id",property = "charpId"),
            @Result(column = "book_name",property = "bookName"),
            @Result(column = "charp_name",property = "charpName"),
            @Result(column = "sec_name",property = "secName"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "view",property = "view"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "creators",property = "creators")
    })
    LiterContent selectAdoptConent(Long id);

    @Select("SELECT no,title,summary,content,book_name,charp_name,sec_name,likes,recom_no,adopt,create_by,create_dat " +
            "FROM t_pro_content " +
            "WHERE no = #{no} " +
            "AND is_del = 0 " )
    @Results(value = {
            @Result(column = "no",property = "no"),
            @Result(column = "title",property = "title"),
            @Result(column = "summary",property = "summary"),
            @Result(column = "content",property = "content"),
            @Result(column = "file",property = "file"),
            @Result(column = "charp_id",property = "charpId"),
            @Result(column = "book_name",property = "bookName"),
            @Result(column = "charp_name",property = "charpName"),
            @Result(column = "sec_name",property = "secName"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "view",property = "view"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "no",property = "recommends",many = @Many(select="com.myIsoland.mapper.product.RecommendMapper.selectHotRecommend",
                    fetchType = FetchType.EAGER))
    })
    LiterContent selectLiterContentRecom(String no);

    /**
     *@Author:THINKPAD
     *@Description:根据id获取文学创作内容
     * @param no
     *@Return:com.myIsoland.enitity.product.LiterContent
     *@Data:13:18 2020/2/3
     **/
    @Select("SELECT no,title,summary,book_name,charp_name,sec_name,likes,recom_no,adopt,create_by,create_dat " +
            "FROM t_pro_content " +
            "WHERE no = #{no} " +
            "AND is_del = 0 " )
    @Results(value = {
            @Result(column = "no",property = "no"),
            @Result(column = "title",property = "title"),
            @Result(column = "summary",property = "summary"),
            @Result(column = "content",property = "content"),
            @Result(column = "file",property = "file"),
            @Result(column = "charp_id",property = "charpId"),
            @Result(column = "book_name",property = "bookName"),
            @Result(column = "charp_name",property = "charpName"),
            @Result(column = "sec_name",property = "secName"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "view",property = "view"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "create_dat",property = "createDat")
    })
    LiterContent selectLiterContentById(String no);

    @Select("SELECT no,title,summary,book_name,charp_name,sec_name,likes,recom_no,adopt,create_dat " +
            "FROM t_pro_content " +
            "WHERE create_by = #{userId} " +
            "AND create_dat <= #{date} " +
            "AND is_del = 0 " +
            "ORDER BY create_dat DESC " +
            "LIMIT #{page},20" )
    @Results(value = {
            @Result(column = "no",property = "no"),
            @Result(column = "title",property = "title"),
            @Result(column = "summary",property = "summary"),
            @Result(column = "content",property = "content"),
            @Result(column = "file",property = "file"),
            @Result(column = "charp_id",property = "charpId"),
            @Result(column = "book_name",property = "bookName"),
            @Result(column = "charp_name",property = "charpName"),
            @Result(column = "sec_name",property = "secName"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "view",property = "view"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "create_dat",property = "createDat")
    })
    List<LiterContent> selectUserLiterContentByDate(@Param("userId") String userId,@Param("date") Date date,@Param("page") int page);

    @Update("UPDATE t_pro_content " +
            "SET likes = likes + 1,favorer = CONCAT(favorer,#{userId}) " +
            "WHERE no = #{no} " +
            "AND is_del = 0 ")
    int updateLikeSts(@Param("userId") String userId,@Param("no") String no);

}
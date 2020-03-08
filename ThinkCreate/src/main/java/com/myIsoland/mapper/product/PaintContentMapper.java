package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.PaintContent;
import com.myIsoland.enitity.product.PaintingPart;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface PaintContentMapper extends BaseMapper<PaintContent> {

    @Select("SELECT c.id,c.title,c.photo,c.cover,c.part_id,c.likes,c.recom_no,c.adopt,c.create_by,u.nickname,u.avatar " +
            "FROM t_pro_paint_content as c LEFT JOIN t_sys_user as u " +
            "ON c.part_id = #{partId} AND c.id_del = 0 " +
            "WHERE c.create_by = u.id AND u.is_del = 0 " +
            "ORDER BY c.adopt, c.create_dat DESC  LIMIT #{start},20")
    @Results(id = "paintContent",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "photo",property = "photo"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "part_id",property = "partId"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "nickname",property = "creator"),
            @Result(column = "avatar",property = "createAvat")
    })
    List<PaintContent> selectPaintContent(@Param("partId") int partId, @Param("start") int start);

    @Select("SELECT no,title,image,paint_id,paint_name,likes，recom_no,part_name,sec_name,adopt,create_by,create_dat " +
            "FROM t_pro_paint_content " +
            "WHERE no = #{no} " +
            "AND is_del = 0 " )
    @Results(id = "content",value = {
            @Result(column = "no",property = "no"),
            @Result(column = "title",property = "title"),
            @Result(column = "image",property = "image"),
            @Result(column = "part_id",property = "partId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "pain_name",property = "paintName"),
            @Result(column = "sec_name",property = "secName"),
            @Result(column = "part_name",property = "partName"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "create_dat",property = "createDat")
    })
    PaintContent selectPaintContentById(String no);

    @Select("SELECT no,title,image,paint_id,paint_name,likes，recom_no,part_name,sec_name,adopt,create_by,create_dat " +
            "FROM t_pro_paint_content " +
            "WHERE no = #{no} " +
            "AND is_del = 0 " )
    @Results(value = {
            @Result(column = "no",property = "no"),
            @Result(column = "title",property = "title"),
            @Result(column = "image",property = "image"),
            @Result(column = "part_id",property = "partId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "pain_name",property = "paintName"),
            @Result(column = "sec_name",property = "secName"),
            @Result(column = "part_name",property = "partName"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "no",property = "recommends",many = @Many(select="com.myIsoland.mapper.product.RecommendMapper.selectHotRecommend",
                    fetchType = FetchType.EAGER))
    })
    PaintContent selectPaintContentRecom(String no);
    /**
     *@Author:THINKPAD
     *@Description:根据绘画id获取采纳作品
     * @param id
     *@Return:com.myIsoland.enitity.product.PaintingPart
     *@Data:20:21 2020/1/31
     **/
    @Select("SELECT no,title,image,recom_no,adopt,create_by,create_dat " +
            "FROM t_pro_paint_content " +
            "WHERE part_id = #{id} " +
            "AND adopt = 1 " +
            "AND is_del = 0 " +
            "LIMIT 1 " +
            "UNION" +
            "SELECT count(no) as creators " +
            "FROM t_pro_paint_content " +
            "WHERE part_id = #{id} " +
            "AND is_del = 0 ")
    @Results(value = {
            @Result(column = "no",property = "no"),
            @Result(column = "title",property = "title"),
            @Result(column = "image",property = "image"),
            @Result(column = "part_id",property = "partId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "pain_name",property = "paintName"),
            @Result(column = "sec_name",property = "secName"),
            @Result(column = "part_name",property = "partName"),
            @Result(column = "recom_no",property = "recomNo"),
            @Result(column = "likes",property = "likes"),
            @Result(column = "adopt",property = "adopt"),
            @Result(column = "create_by",property = "createBy"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "creators",property = "creators")
    })
    PaintContent selectAdoptConent(Long id);


    @Update("UPDATE t_pro_paint_content " +
            "SET likes = likes + 1,favorer = CONCAT(favorer,#{userId}) " +
            "WHERE no = #{no} " +
            "AND is_del = 0 ")
    int updateLikeSts(@Param("userId") String userId, @Param("no") String no);
}
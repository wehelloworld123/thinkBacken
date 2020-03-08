package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.PaintingPart;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface PaintingPartMapper extends BaseMapper<PaintingPart> {
    /**
     *@Author:THINKPAD
     *@Description:根据绘画uid获取绘画部分
     * @param uid
     *@Return:com.myIsoland.enitity.product.PaintingPart
     *@Data:13:02 2020/1/31
     **/
    @Select("SELECT id,title,intoduce,requirement,paint_id,liter_id,poem_id,root_ord,ord,is_lock,finish,create_dat  " +
            "FROM t_pro_paint_part " +
            "WHERE paint_id = #{uid} " +
            "AND is_lock = 0 " +
            "AND root = 0 " +
            "AND finish = 0 " +
            "AND is_del = 0 " +
            "ORDER BY root_ord,ord ASC " +
            "limit 1")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "subject",property = "title"),
            @Result(column = "intoduce",property = "introduce"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "number",property = "number"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "order"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat")
    })

    PaintingPart selectPaintingPartByUid(String uid);


    /**
     *@Author:THINKPAD
     *@Description:根据艺术绘画id获取章节以及子章节信息
     * @param paintId
     *@Return:java.util.List<com.myIsoland.enitity.product.PaintingPart>
     *@Data:16:00 2020/1/31
     **/
    @Select("SELECT id,subject,paint_id,liter_id,poem_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_charpt " +
            "WHERE paint_id = #{paintId} " +
            "AND root = 1 " +
            "AND is_del = 0 " +
            "ORDER BY order DESC")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "subject",property = "subject"),
            @Result(column = "intoduce",property = "introduce"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "number",property = "number"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "order"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "id",column = "id",many = @Many(select="com.myIsoland.mapper.product.PaintingPartMapper.selectChirdParts",
                    fetchType = FetchType.EAGER))
    })
    List<PaintingPart> selectPaintingParts(String paintId);


    @Select("SELECT id,subject,paint_id,liter_id,poem_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_charpt " +
            "WHERE root_id = #{partId} " +
            "AND is_del = 0 " +
            "ORDER BY order ASC")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "subject",property = "subject"),
            @Result(column = "intoduce",property = "introduce"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "number",property = "number"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "order"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat")
    })
    /**
     *@Author:THINKPAD
     *@Description:根据书籍章节id获取子章节信息
     * @param partId
     *@Return:java.util.List<com.myIsoland.enitity.product.PaintingPart>
     *@Data:15:59 2020/1/31
     **/
    List<PaintingPart> selectChirdParts(Long partId);


    /**
     *@Author:THINKPAD
     *@Description:根据书籍id获取章节内容和采纳作品
     * @param id
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:21:46 2020/1/28
     **/
    @Select("SELECT id,title,intoduce,requirement,root,root_id,book_id,paint_id,poem_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_paint_part " +
            "WHERE id = #{id} " +
            "AND root = 0 " +
            "AND is_del = 0 ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "subject",property = "title"),
            @Result(column = "intoduce",property = "introduce"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "number",property = "number"),
            @Result(column = "root",property = "root"),
            @Result(column = "root_id",property = "rootId"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "order"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "paintContent",column = "id",one = @One(select="com.myIsoland.mapper.product.PaintContentMapper.selectAdoptConent",
                    fetchType = FetchType.EAGER))
    })
    PaintingPart selectPartById(long id);
}

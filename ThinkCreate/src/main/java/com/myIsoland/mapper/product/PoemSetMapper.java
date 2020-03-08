package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.PoemSet;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface PoemSetMapper extends BaseMapper<PoemSet> {
    /**
     *@Author:THINKPAD
     *@Description:根据诗词集id获取未完成章节
     * @param uid
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:21:46 2020/1/28
     **/
    @Select("SELECT id,charpter,describe,creators,number,root_ord,ord,poetry_id,liter_id,paint_id,is_lock,finish,create_dat " +
            "FROM t_pro_poemset " +
            "WHERE poetry_id = #{uid} " +
            "AND is_lock = 0 " +
            "AND finish = 0 " +
            "AND root = 0 " +
            "AND is_del = 0 " +
            "ODER BY root_ord,ord ASC " +
            "LIMIT 1")

    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "describe",property = "describe"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "number",property = "number"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "order"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat"),
    })

    PoemSet selectSetByUid(String uid);

    /**
     *@Author:THINKPAD
     *@Description:根据诗词id获取章节以及子章节信息
     * @param poetryId
     *@Return:java.util.List<com.myIsoland.enitity.product.PaintingPart>
     *@Data:16:00 2020/1/31
     **/
    @Select("SELECTid,charpter,describe,number,root,poetry_id,liter_id,paint_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_poemset " +
            "WHERE poetry_id = #{poetryId} " +
            "AND root = 1 " +
            "AND is_del = 0 " +
            "ORDER BY order DESC")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "describe",property = "describe"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "number",property = "number"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "order"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "id",column = "id",many = @Many(select="com.myIsoland.mapper.product.PoemSetMapper.selectChirdSets",
                    fetchType = FetchType.EAGER))
    })
    List<PoemSet> selectPoemSets(String poetryId);

    /**
     *@Author:THINKPAD
     *@Description:根据诗词章节id获取子章节信息
     * @param id
     *@Return:java.util.List<com.myIsoland.enitity.product.PoemSet>
     *@Data:15:59 2020/1/31
     **/
    @Select("SELECT id,charpter,describe,number,root,poetry_id,liter_id,paint_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_poemset " +
            "WHERE root_id = #{id} " +
            "AND is_del = 0 " +
            "ORDER BY order ASC")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "describe",property = "describe"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "number",property = "number"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "order"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat")
    })
    List<PoemSet> selectChirdSets(Long id);



    /**
     *@Author:THINKPAD
     *@Description:根据诗歌章节id获取章节内容和采纳作品
     * @param id
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:21:46 2020/1/28
     **/
    @Select("SELECT id,charpter,describe,requirement,number,root,poetry_id,liter_id,paint_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_poemset " +
            "WHERE id = #{id} " +
            "AND root = 0 " +
            "AND is_del = 0 ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "describe",property = "describe"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "number",property = "number"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "order"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "literContent",column = "id",one = @One(select="com.myIsoland.mapper.product.LiterContentMapper.selectAdoptConent",
                    fetchType = FetchType.EAGER))
    })
    PoemSet selectChaptById(long id);
}

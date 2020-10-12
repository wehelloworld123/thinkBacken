package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.PoemSet;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

public interface PoemSetMapper extends BaseMapper<PoemSet> {
    /**
     *@Author:THINKPAD
     *@Description:根据诗词集id获取未完成章节
     * @param uid
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:21:46 2020/1/28
     **/
    @Select("SELECT id,charpter,description,creators,number,root_ord,ord,poetry_id,liter_id,paint_id,is_lock,finish,create_dat " +
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
            @Result(column = "description",property = "description"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "number",property = "number"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "ord"),
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
    @Select("SELECT id,charpter,description,number,root,poetry_id,liter_id,paint_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_poemset " +
            "WHERE poetry_id = #{poetryId} " +
            "AND root = 1 " +
            "AND is_del = 0 " +
            "ORDER BY ord DESC")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "description",property = "description"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "number",property = "number"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "ord"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "id",column = "id",many = @Many(select="com.myIsoland.mapper.product.PoemSetMapper.selectChirdSets",
                    fetchType = FetchType.EAGER))
    })
    List<PoemSet> selectPoemSets(String poetryId);


    /**
     *@Author:THINKPAD
     *@Description:根据章节id获取诗歌以及章节信息
     * @param id
     *@Return:java.util.List<com.myIsoland.enitity.product.PaintingPart>
     *@Data:16:00 2020/1/31
     **/
    @Select("SELECT uid,seter,name,topic,purpose,description,liter_id,paint_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_poemset as A left join t_pro_poetry as B " +
            "ON A.poetry_id = B.uid " +
            "AND B.is_del = 0 " +
            "WHERE A.id = #{id} " +
            "AND A.is_del = 0 ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "description",property = "description"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "number",property = "number"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "ord"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "id",column = "id",many = @Many(select="com.myIsoland.mapper.product.PoemSetMapper.selectChirdSets",
                    fetchType = FetchType.EAGER))
    })
    Map<String,Object> selectPoetryBySetId(Long id);

    /**
     *@Author:THINKPAD
     *@Description:根据诗词章节id获取子章节信息
     * @param id
     *@Return:java.util.List<com.myIsoland.enitity.product.PoemSet>
     *@Data:15:59 2020/1/31
     **/
    @Select("SELECT id,charpter,description,number,root,poetry_id,liter_id,paint_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_poemset " +
            "WHERE root_id = #{id} " +
            "AND is_del = 0 " +
            "ORDER BY ord ASC")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "description",property = "description"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "number",property = "number"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "ord"),
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
    @Select("SELECT id,charpter,description,requirement,pic,number,root,poetry_id,liter_id,paint_id,ord,is_lock,finish,create_dat " +
            "FROM t_pro_poemset " +
            "WHERE id = #{id} " +
            "AND root = 0 " +
            "AND is_del = 0 ")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "description",property = "description"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "number",property = "number"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "liter_id",property = "literId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "pic",property = "pic"),
            @Result(column = "ord",property = "ord"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "poemContent",column = "id",one = @One(select="com.myIsoland.mapper.product.PoemContentMapper.selectAdoptConent",
                    fetchType = FetchType.EAGER))
    })
    PoemSet selectChaptById(long id);



    @Select("SELECT A.id,A.charpter,A.description,A.pic,A.poetry_id,A.creators,A.create_dat,B.name,B.views " +
            "FROM t_pro_poemset as A INNER JOIN t_pro_poetry as B " +
            "ON A.is_lock = 0 " +
            "AND A.finish = 0" +
            "AND A.is_del = 0" +
            "AND B.uid = A.poetry_id " +
            "AND B.is_del = 0" +
            "ORDER BY A.creators DESC " )
    @Results(id = "poetryMap",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "charpter",property = "charpter"),
            @Result(column = "description",property = "description"),
            @Result(column = "pic",property = "pic"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "poetry_id",property = "poetryId"),
            @Result(column = "name",property = "poetryName"),
            @Result(column = "views",property = "views")
    })
    /**
     *@Author:THINKPAD
     *@Description:根据热门获取小集列表
     *@Return:java.util.List<com.myIsoland.enitity.product.PoemSet>
     *@Data:22:38 2020/6/8
     **/
    List<PoemSet> selectPoemSetByHotNo();
}

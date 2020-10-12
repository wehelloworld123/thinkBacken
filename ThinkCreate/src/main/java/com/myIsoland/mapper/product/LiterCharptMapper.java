package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.LiterCharpt;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;

public interface LiterCharptMapper extends BaseMapper<LiterCharpt> {

    /**
     *@Author:THINKPAD
     *@Description:根据书籍id获取未完成章节
     * @param uid
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:21:46 2020/1/28
     **/
    @Select("SELECT id,title,introduce,requirement,book_id,paint_id,poem_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_charpt " +
            "WHERE book_id = #{uid} " +
            "AND is_lock = 0 " +
            "AND finish = 0 " +
            "AND root = 0 " +
            "AND is_del = 0 " +
            "ORDER BY root_ord,ord ASC " +
            "LIMIT 1")
    @Results(id = "literChaptMap",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "intoduce",property = "introduce"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "book_id",property = "bookId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "order"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat"),
    })
    LiterCharpt selectLiterByUid(String uid);


    /**
     *@Author:THINKPAD
     *@Description:根据书籍id获取章节内容和采纳作品
     * @param id
     *@Return:com.myIsoland.enitity.product.LiterCharpt
     *@Data:21:46 2020/1/28
     **/
    @Select("SELECT id,title,introduce,requirement,creators,book_id,paint_id,poem_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_charpt " +
            "WHERE id = #{id} " +
            "AND root = 0 " +
            "AND is_del = 0 ")
    @ResultMap(value = "literChaptMap")
/*            @Result(property = "literContent",column = "id",one = @One(select="com.myIsoland.mapper.product.LiterContentMapper.selectAdoptConent",
                    fetchType = FetchType.EAGER))*/
    LiterCharpt selectChaptById(long id);

    @Select("SELECT id,title,book_id,paint_id,poem_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_charpt " +
            "WHERE book_id = #{bookId} " +
            "AND root = 1 " +
            "AND is_del = 0 " +
            "ORDER BY ord ASC")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "intoduce",property = "introduce"),
            @Result(column = "requirement",property = "requirement"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "book_id",property = "bookId"),
            @Result(column = "paint_id",property = "paintId"),
            @Result(column = "root_ord",property = "rootOrder"),
            @Result(column = "ord",property = "order"),
            @Result(column = "is_lock",property = "isLock"),
            @Result(column = "finish",property = "finish"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(property = "charpts",column = "id",many = @Many(select="com.myIsoland.mapper.product.LiterCharptMapper.selectChirdCharps",
                    fetchType = FetchType.EAGER))
    })
    /**
     *@Author:THINKPAD
     *@Description:根据书籍id获取章节以及子章节信息
     * @param bookId
     *@Return:java.util.List<com.myIsoland.enitity.product.LiterCharpt>
     *@Data:21:52 2020/1/28
     **/
    List<LiterCharpt> selectBookCharps(String bookId);

    @Select("SELECT id,title,book_id,paint_id,poem_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_charpt " +
            "WHERE root_id = #{id} " +
            "AND is_del = 0 " +
            "ORDER BY ord DESC")
     @ResultMap(value = "literChaptMap")
    /**
     *@Author:THINKPAD
     *@Description:根据书籍章节id获取子章节信息
     * @param charpId
     *@Return:java.util.List<com.myIsoland.enitity.product.LiterCharpt>
     *@Data:21:52 2020/1/28
     **/
    List<LiterCharpt> selectChirdCharps(Long id);


    @Select("SELECT A.id,A.title,A.introduce,A.book_id,A.creators,A.create_dat,B.label,B.name,B.views " +
            "FROM t_pro_charpt as A LEFT JOIN t_pro_liter as B " +
            "ON B.uid = A.book_id " +
            "AND B.is_del = 0" +
            "WHERE A.chap_type = #{kind} " +
            "AND A.is_lock = 0 " +
            "AND A.finish =0 " +
            "AND A.is_del = 0" +
            "ORDER BY A.create_dat DESC " )
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "introduce",property = "introduce"),
            @Result(column = "book_id",property = "bookId"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "label",property = "label"),
            @Result(column = "name",property = "bookName"),
            @Result(column = "views",property = "views")
    })
    /**
     *@Author:THINKPAD
     *@Description:根据类型获取章节列表
     * @param kind
     *@Return:java.util.List<com.myIsoland.enitity.product.LiterCharpt>
     *@Data:22:13 2020/6/8
     **/
    List<LiterCharpt> selectChapterByType(@Param("kind")int kind);




    @Select("SELECT A.id,A.title,A.introduce,A.book_id,A.creators,A.create_dat,B.label,B.name,B.views " +
            "FROM t_pro_charpt as A LEFT JOIN t_pro_liter as B " +
            "ON B.uid = A.book_id " +
            "AND B.is_del = 0" +
            "WHERE A.create_dat <= #{date} " +
            "AND A.is_lock = 0 " +
            "AND A.finish =0 " +
            "AND A.is_del = 0" +
            "ORDER BY A.create_dat DESC " )
    @Results(id = "chapterMap",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "introduce",property = "introduce"),
            @Result(column = "book_id",property = "bookId"),
            @Result(column = "creators",property = "creators"),
            @Result(column = "create_dat",property = "createDat"),
            @Result(column = "label",property = "label"),
            @Result(column = "name",property = "name"),
            @Result(column = "views",property = "views")
    })
    /**
     *@Author:THINKPAD
     *@Description:根据最新获取章节列表
     * @param date
     * @param startIndex
     * @param pageSize
     *@Return:java.util.List<com.myIsoland.enitity.product.LiterCharpt>
     *@Data:22:38 2020/6/8
     **/
    List<LiterCharpt> selectChapterByDate(@Param("date") Date date);



    @Select("SELECT A.id,A.title,A.introduce,A.book_id,A.creators,A.create_dat,B.label,B.name,B.views " +
            "FROM t_pro_charpt as A LEFT JOIN t_pro_liter as B " +
            "ON  B.uid = A.book_id " +
            "AND B.is_del = 0" +
            "WHERE A.is_lock = 0 " +
            "AND A.finish =0 " +
            "AND A.is_del = 0" +
            "ORDER BY A.creators DESC ")
    @ResultMap(value = "chapterMap")
    /**
     *@Author:THINKPAD
     *@Description:根据热度获取章节列表
     *@Return:java.util.List<com.myIsoland.enitity.product.LiterCharpt>
     *@Data:22:38 2020/6/8
     **/
    List<LiterCharpt> selectChapterByHot( );



    @Select("SELECT A.id,A.title,A.introduce,A.book_id,A.creators,A.create_dat,B.label,B.name,B.views " +
            "FROM t_pro_charpt as A LEFT JOIN t_pro_liter as B " +
            "ON  B.uid = A.book_id " +
            "AND B.is_del = 0" +
            "AND A.is_lock = 0 " +
            "AND A.finish = 1 " +
            "AND A.is_del = 0" +
            "ORDER BY A.creators DESC " )
    @ResultMap(value = "chapterMap")
    /**
     *@Author:THINKPAD
     *@Description:根据热度获取已完成章节列表
     *@Return:java.util.List<com.myIsoland.enitity.product.LiterCharpt>
     *@Data:22:38 2020/6/8
     **/
    List<LiterCharpt> selectFinChapterByHot();
}

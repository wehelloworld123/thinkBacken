package com.myIsoland.mapper.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.product.LiterCharpt;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
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
    @Results({
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
    @Select("SELECT id,title,intoduce,requirement,book_id,paint_id,poem_id,root_ord,ord,is_lock,finish,create_dat " +
            "FROM t_pro_charpt " +
            "WHERE id = #{id} " +
            "AND root = 0 " +
            "AND is_del = 0 ")
    @Results({
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
            @Result(property = "literContent",column = "id",one = @One(select="com.myIsoland.mapper.product.LiterContentMapper.selectAdoptConent",
                    fetchType = FetchType.EAGER))
    })
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
            @Result(column = "create_dat",property = "createDat")
    })
    /**
     *@Author:THINKPAD
     *@Description:根据书籍章节id获取子章节信息
     * @param charpId
     *@Return:java.util.List<com.myIsoland.enitity.product.LiterCharpt>
     *@Data:21:52 2020/1/28
     **/
    List<LiterCharpt> selectChirdCharps(Long id);
}

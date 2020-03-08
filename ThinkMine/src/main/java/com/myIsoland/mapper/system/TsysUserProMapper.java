package com.myIsoland.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.system.TSysUserProduct;
import com.myIsoland.model.ProductModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TsysUserProMapper extends BaseMapper<TSysUserProduct> {
    @Select("SELECT l.uid as pro_id,l.name as pro_name,l.fin_charp,l.fin_section,l.deadline,up.kind,up.inviter,up.create_dat " +
            "FROM t_pro_liter l INNER JOIN t_sys_user_pro up " +
            "ON up.user_id=#{userId} AND up.kind=#{kind} AND up.status=#{status} AND up.is_del=0 " +
            "WHERE l.uid=up.pro_id AND l.is_del=0 ORDER BY up.create_dat DESC LIMIT #{start},20")
    @Results({
            @Result(column = "pro_id",property = "proId"),
            @Result(column = "pro_name",property = "proName"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "fin_charp",property = "finCharp"),
            @Result(column = "fin_section",property = "finSection"),
            @Result(column = "deadline",property = "deadline"),
            @Result(column = "inviter",property = "inviter"),
            @Result(column = "create_dat",property = "createDat")

    })
    List<ProductModel> selectUserLiter(@Param("userId") String userId, @Param("kind") int kind, @Param("status") int status, @Param("start") int start);


    @Select("SELECT p.uid as pro_id,p.name as pro_name,p.fin_part as fin_charp,p.fin_section,p.deadline,up.kind,up.inviter,up.create_dat " +
            "FROM t_pro_paint as p INNER JOIN t_sys_user_pro as up " +
            "ON up.user_id=#{userId} AND up.kind=#{kind} AND up.status=#{status} AND up.is_del=0 " +
            "WHERE p.uid=up.pro_id AND p.is_del=0 ORDER BY up.create_dat DESC LIMIT #{start},20")
    @Results({
            @Result(column = "pro_id",property = "proId"),
            @Result(column = "pro_name",property = "proName"),
            @Result(column = "cover",property = "cover"),
            @Result(column = "kind",property = "kind"),
            @Result(column = "fin_charp",property = "finCharp"),
            @Result(column = "fin_section",property = "finSection"),
            @Result(column = "deadline",property = "deadline"),
            @Result(column = "inviter",property = "inviter"),
            @Result(column = "create_dat",property = "createDat")

    })
    List<ProductModel> selectUserPaint(@Param("userId") String userId, @Param("kind") int kind, @Param("status") int status, @Param("start") int start);
}

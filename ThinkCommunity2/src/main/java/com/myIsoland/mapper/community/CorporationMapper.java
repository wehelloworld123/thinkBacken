package com.myIsoland.mapper.community;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myIsoland.enitity.community.Corporation;
import com.myIsoland.enitity.system.TsysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface CorporationMapper extends BaseMapper<Corporation> {

    @Select("SELECT u.id,u.nickname,u.avatar,u.sex" +
            "FROM t_sys_user u INNER JOIN t_com_user_crop uc" +
            "ON uc.c_uid=#{corpId} AND uc.is_del=0 AND uc.state=1 " +
            "WHERE u.id=uc.u_uid AND uc.is_del=0")
    @Results(id = "userInfo",value = {
            @Result(property = "nickname",column = "nickname"),
            @Result(property = "id",column = "id"),
            @Result(property = "avatar",column = "avatar"),
            @Result(property = "sex",column = "sex")
    })
    List<TsysUser> selectCorpUser(String corpId);


    /**
     *@Author:THINKPAD
     *@Description:更新喜欢状态
     * @param userId
     * @param id
     *@Return:int
     *@Data:23:01 2020/2/7
     **/
    @Update("UPDATE t_com_corporation " +
            "SET likes = likes + 1,favorer = CONCAT(favorer,#{userId}) " +
            "WHERE id = #{id} " +
            "AND is_del = 0 ")
    int updateCorpLike(@Param("userId") String userId, @Param("id") String id);

    /**
     *@Author:THINKPAD
     *@Description:根据id获取详细信息
     * @param userId
     * @param corpId
     *@Return:com.myIsoland.enitity.community.Corporation
     *@Data:17:31 2020/2/8
     **/
    @Select("SELECT id,name,description,slogan,logo,label,member_no,#{userid} as userid," +
            "typ,star,likes,instr(favorer,#{userid}) as is_like " +
            "FROM t_com_corporation " +
            "WHERE id = #{corpId} " +
            "AND is_del = 0")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "description",column = "description"),
            @Result(property = "slogan",column = "slogan"),
            @Result(property = "logo",column = "logo"),
            @Result(property = "label",column = "label"),
            @Result(property = "memberNo",column = "member_no"),
            @Result(property = "type",column = "typ"),
            @Result(property = "star",column = "star"),
            @Result(property = "likes",column = "likes"),
            @Result(property = "isLike",column = "is_like"),
            @Result(property = "isPart",column = "{id=id,userid=userid}",one = @One(select="com.myIsoland.mapper.community.UserCorpMapper.queryJionCorp",fetchType = FetchType.EAGER))
    })
    Corporation selectCorpById(@Param("userid") String userId,@Param("corpId") String corpId);
}

package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.Comment;
import com.myIsoland.enitity.product.UserCreation;
import com.myIsoland.enums.RecomType;
import com.myIsoland.mapper.product.UserCreationMapper;
import com.myIsoland.model.ResultSet;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class UserCreationServiceImpl extends ServiceImpl<UserCreationMapper,UserCreation> implements UserCreationService{

    @Override
    public List<Map<String, Object>> GetCreatPartInfo(String createId, RecomType type,int startIndex,int pageSize) {
/*        if(RecomType.LITERATURE==type) createId = ProjectConstant.LITERPREFIX + createId;
        else if (RecomType.PAINTING==type)  createId = ProjectConstant.PAINTINGPREFIX + createId;
        else if (RecomType.POEMTY==type)  createId = ProjectConstant.POETRYPREFIX + createId;*/
        PageHelper.offsetPage(startIndex,pageSize,true);
        LambdaQueryWrapper<UserCreation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserCreation::getCreationId,createId)
                .eq(UserCreation::getTyp,type.getValue())
                .eq(UserCreation::getIsDel,0)
                .orderByDesc(UserCreation::getCreateDat)
                .last("LIMIT 10")
                .select(UserCreation::getAvatar,UserCreation::getUserId,UserCreation::getNickname,UserCreation::getTitle,UserCreation::getProNo);
        return this.baseMapper.selectMaps(wrapper);
    }

    @Override
    public ResultSet<UserCreation> GetUserAdoptContent(String userId, Date date, int start,int limit) {

        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<UserCreation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UserCreation::getUserId,userId)
                .le(UserCreation::getCreateDat,date)
                .eq(UserCreation::getAdopt,1)
                .eq(UserCreation::getIsDel,0)
                .orderByDesc(UserCreation::getCreateDat)
                .select(UserCreation::getId,UserCreation::getTyp,UserCreation::getCreationId,UserCreation::getCreationNm,UserCreation::getCharpId,UserCreation::getCharpNm,BaseEntity::getCreateDat,UserCreation::getContent,UserCreation::getTitle,UserCreation::getPicture);
        List<UserCreation> list = this.baseMapper.selectList(wrapper);
        PageInfo<UserCreation> pageInfo = new PageInfo<>(list);
        ResultSet<UserCreation> resultSet = new ResultSet<>();
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return  resultSet;
    }

    @Override
    public List<Map<String,Object>> GetAuthorInfoByPoetryId(String poetryId, Date queryDate,int start,int limit) {
        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<UserCreation> wrapper = new QueryWrapper<>();
        wrapper.select("pro_no as no","user_id as userid","nickname","title","avatar")
                .lambda()
                .eq(UserCreation::getCreationId,poetryId)
                .eq(UserCreation::getTyp,RecomType.POEMTY)
                .lt(BaseEntity::getCreateDat,queryDate)
                .eq(UserCreation::getAdopt,1);
               /* .select(UserCreation::getUserId,UserCreation::getAvatar,UserCreation::getNickname,UserCreation::getTitle,getn);*/
        return this.baseMapper.selectMaps(wrapper);
    }
}

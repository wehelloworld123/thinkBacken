package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.UserCreation;
import com.myIsoland.enums.RecomType;
import com.myIsoland.mapper.product.UserCreationMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Service
public class UserCreationServiceImpl extends ServiceImpl<UserCreationMapper,UserCreation> implements UserCreationService{

    @Override
    public List<Map<String, Object>> GetCreatPartInfo(String createId, RecomType type) {
/*        if(RecomType.LITERATURE==type) createId = ProjectConstant.LITERPREFIX + createId;
        else if (RecomType.PAINTING==type)  createId = ProjectConstant.PAINTINGPREFIX + createId;
        else if (RecomType.POEMTY==type)  createId = ProjectConstant.POETRYPREFIX + createId;*/
        LambdaQueryWrapper<UserCreation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserCreation::getCreationId,createId)
                .eq(UserCreation::getTyp,type.getValue())
                .eq(UserCreation::getIsDel,0)
                .orderByDesc(UserCreation::getCreateDat)
                .last("LIMIT 10")
                .select(UserCreation::getAvatar);
        return this.baseMapper.selectMaps(wrapper);
    }

    @Override
    public List<UserCreation> GetUserAdoptContent(String userId, Date date, int page) {
        return this.baseMapper.selectUserCreationByDate(userId,date,page);
    }
}

package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enitity.product.ProComment;
import com.myIsoland.enums.RecomType;
import com.myIsoland.mapper.product.ProCommentMapper;
import com.myIsoland.model.ResultSet;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProCommentServiceImpl  extends ServiceImpl<ProCommentMapper,ProComment> implements ProCommentService {
    @Override
    public ResultSet<ProComment> GetProCommentsByDate(String creationId, RecomType type, Date date, int start, int limit) {
        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<ProComment> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(ProComment::getCreationId,creationId)
                .eq(ProComment::getTyp,type.getValue())
                .le(ProComment::getCreateDat,date)
                .eq(ProComment::getIsDel,0)
                .select(ProComment::getId,ProComment::getContent,ProComment::getCreator,ProComment::getCreatorAvat, BaseEntity::getCreateDat);
        List<ProComment> list = this.baseMapper.selectList(wrapper);
        PageInfo<ProComment> pageInfo = new PageInfo<>(list);
        ResultSet<ProComment> resultSet = new ResultSet<>();
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return  resultSet;
    }
}

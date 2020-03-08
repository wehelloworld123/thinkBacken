package com.myIsoland.service.personal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.personal.RecomBook;
import com.myIsoland.mapper.personal.RecomBookMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RecomBookServiceImpl extends ServiceImpl<RecomBookMapper,RecomBook> implements RecomBookService {

    @Override
    public List<RecomBook> GetRecomBooks(Date date) {
        LambdaQueryWrapper<RecomBook> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(RecomBook::getCreateDat,date)
                .eq(RecomBook::getIsDel,0)
                .orderByDesc(RecomBook::getCreateDat)
                .last("LIMIT 3")
                .select(RecomBook::getId,RecomBook::getBookname,RecomBook::getLabel,RecomBook::getReason,RecomBook::getSource,RecomBook::getRootId,RecomBook::getCreateDat);

        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int DeleteRecomBook(int id) {
        return this.baseMapper.deleteById(id);
    }
}

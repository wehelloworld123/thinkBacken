package com.myIsoland.service.personal;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.personal.Saying;
import com.myIsoland.mapper.personal.SayingMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class SayingServiceImpl extends ServiceImpl<SayingMapper,Saying> implements SayingService {
    @Override
    public List<Saying> GetSayingList(Date date) {
        LambdaQueryWrapper<Saying> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(Saying::getCreateDat,date)
                .eq(Saying::getIsDel,0)
                .select(Saying::getRootId)
                .last("LIMIT 10");
        IPage<Saying> curPage = new Page<>(2,4);
        curPage = this.baseMapper.selectPage(curPage,wrapper);
        return curPage.getRecords();
    }



    @Override
    public int DeleteSaying(int id) {
        return this.baseMapper.deleteById(id);
    }
}

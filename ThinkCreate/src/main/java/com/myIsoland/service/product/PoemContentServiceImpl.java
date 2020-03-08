package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.mapper.product.PoemContentMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PoemContentServiceImpl extends ServiceImpl<PoemContentMapper,PoemContent> implements PoemContentService {


    @Override
    public int UpdatePoemContent(PoemContent data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public int UpdateLikeSts(String userId, String no) {
        userId = userId + ";";
        return this.baseMapper.updateLikeSts(userId,no);
    }

    @Override
    public int DelUserPoemContent(String userId, String no) {
        LambdaQueryWrapper<PoemContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PoemContent::getNo,no)
                .eq(PoemContent::getCreateBy,userId)
                .eq(PoemContent::getIsDel,0);
        return this.baseMapper.delete(wrapper);
    }

    @Override
    public PoemContent GetPoemContentById(String no) {
        return this.baseMapper.selectPoemContentRecom(no);
    }

    @Override
    public List<PoemContent> GetContentsOrderByDate(String userId,Long charptId, Date date, List<String> arr) {
        QueryWrapper<PoemContent> wrapper = new QueryWrapper<>();
        wrapper.select("no","brand","title","likes","recom_no","adopt","create_by","create_dat","instr(favorer,"+userId+") as is_like")
                .lambda()
                .eq(PoemContent::getCharpId,charptId)
                .lt(PoemContent::getCreateDat,date)
                .eq(PoemContent::getIsDel,0)
                .notIn(PoemContent::getNo,arr)
                .orderByDesc(PoemContent::getCreateDat)
                .select(PoemContent::getNo, PoemContent::getTitle,PoemContent::getBrand,PoemContent::getLikes,PoemContent::getRecomNo,
                        PoemContent::getAdopt,PoemContent::getCreateBy,PoemContent::getCreateDat)
                .last("LIMIT 15");
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<PoemContent> GetHotPoemContent(String userId,Long charptId) {
        QueryWrapper<PoemContent> wrapper = new QueryWrapper<>();
        wrapper.select("no","brand","title","likes","recom_no","adopt","create_by","create_dat","instr(favorer,"+userId+") as is_like")
                .lambda()
                .eq(PoemContent::getCharpId,charptId)
                .ge(PoemContent::getRecomNo,40)
                .ge(PoemContent::getLikes,100)
                .eq(PoemContent::getAdopt,0)
                .eq(PoemContent::getIsDel,0)
                .orderByDesc(PoemContent::getRecomNo,PoemContent::getLikes)
                .last("LIMIT 6");
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public PoemContent GetUserAdvancePoemContent(String userId, int recomNo, int likes) {
        LambdaQueryWrapper<PoemContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PoemContent::getCreateBy,userId)
                .ge(PoemContent::getRecomNo,recomNo)
                .ge(PoemContent::getLikes,likes)
                .eq(PoemContent::getIsDel,0)
                .orderByDesc(PoemContent::getCreateDat)
                .select(PoemContent::getNo,PoemContent::getBrand,PoemContent::getTitle,PoemContent::getContent,PoemContent::getPoetryName,PoemContent::getCharpName,
                        PoemContent::getLikes,PoemContent::getRecomNo,PoemContent::getCreateDat)
                .last("limit 1");
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<PoemContent> GetUserPoemContentByDate(String userId, Date date, int page) {
        LambdaQueryWrapper<PoemContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PoemContent::getCreateBy,userId)
                .ge(PoemContent::getCreateDat,date)
                .eq(PoemContent::getIsDel,0)
                .orderByDesc(PoemContent::getCreateDat)
                .select(PoemContent::getNo,PoemContent::getBrand,PoemContent::getTitle,PoemContent::getContent,PoemContent::getPoetryName,PoemContent::getCharpName,
                        PoemContent::getLikes,PoemContent::getRecomNo,PoemContent::getCreateDat)
                .last("Limit "+page+",20");
        return this.baseMapper.selectList(wrapper);
    }
}

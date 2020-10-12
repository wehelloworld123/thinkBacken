package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.common.util.StringUtils;
import com.myIsoland.enitity.product.PoemContent;
import com.myIsoland.mapper.product.PoemContentMapper;
import com.myIsoland.model.ResultSet;
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
    public int DelLikeSts(String userId, String no) {
        userId = userId + ";";
        return this.baseMapper.delLikeSts(userId,no);
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
    public PoemContent GetPoemContentById(String no,String userId) {
        return this.baseMapper.selectPoemContentById(no,userId);
    }

    @Override
    public PoemContent GetAdoptPoemContent(Long charpId) {
        LambdaQueryWrapper<PoemContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PoemContent::getCharpId,charpId)
                .eq(PoemContent::getAdopt,1)
                .eq(BaseEntity::getIsDel,0);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public ResultSet<PoemContent> GetContentsOrderByDate(Long charptId, Date date,int start,int limit, List<String> arr) {

        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<PoemContent> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(PoemContent::getCharpId,charptId)
                .lt(PoemContent::getCreateDat,date)
                .eq(PoemContent::getIsDel,0)
                .notIn(PoemContent::getNo,arr)
                .select(PoemContent::getNo, PoemContent::getTitle,PoemContent::getBrand,PoemContent::getLikes,PoemContent::getRecomNo,
                        PoemContent::getAdopt,PoemContent::getCreateBy,PoemContent::getCreateDat)
                .orderByDesc(PoemContent::getCreateDat);
        List<PoemContent> list = this.baseMapper.selectList(wrapper);
        PageInfo<PoemContent> pageInfo = new PageInfo<>(list);
        ResultSet<PoemContent> resultSet = new ResultSet<>();
        resultSet.setCount(pageInfo.getTotal());
        resultSet.setList(list);
        return resultSet;
    }

    @Override
    public List<PoemContent> GetHotPoemContent(String userId,Long charptId) {
        PageHelper.startPage(0,6);
        QueryWrapper<PoemContent> wrapper = new QueryWrapper<>();
        if(userId!=null&& !StringUtils.isEmpty(userId)){
            wrapper.select("no","brand","title","content","likes","recom_no","adopt","create_by","create_dat","instr(favorer,"+userId+") as is_like")
                    .lambda()
                    .eq(PoemContent::getCharpId,charptId)
                    .ge(PoemContent::getRecomNo,40)
                    .ge(PoemContent::getLikes,100)
                    .eq(PoemContent::getAdopt,0)
                    .eq(PoemContent::getIsDel,0)
                    .orderByDesc(PoemContent::getRecomNo,PoemContent::getLikes);
        }else{
            wrapper.select("no","brand","title","content","likes","recom_no","adopt","create_by","create_dat")
                    .lambda()
                    .eq(PoemContent::getCharpId,charptId)
                    .ge(PoemContent::getRecomNo,40)
                    .ge(PoemContent::getLikes,100)
                    .eq(PoemContent::getAdopt,0)
                    .eq(PoemContent::getIsDel,0)
                    .orderByDesc(PoemContent::getRecomNo,PoemContent::getLikes);
        }

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

/*    @Override
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
    }*/

    @Override
    public ResultSet<PoemContent> GetUserPoemContentByDate(String userId,Date date, int start, int limit) {

        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<PoemContent> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PoemContent::getCreateBy,userId)
                .le(PoemContent::getCreateDat,date)
                .eq(PoemContent::getIsDel,0)
                .orderByDesc(PoemContent::getCreateDat)
                .select(PoemContent::getNo,PoemContent::getBrand,PoemContent::getTitle,PoemContent::getContent,PoemContent::getPoetryName,PoemContent::getCharpName,PoemContent::getCharpId,
                        PoemContent::getLikes,PoemContent::getSecName,PoemContent::getCreateDat);
        List<PoemContent> list = this.baseMapper.selectList(wrapper);
        PageInfo<PoemContent> pageInfo = new PageInfo<>(list);
        ResultSet<PoemContent> resultSet = new ResultSet<>();
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return  resultSet;
    }

    @Override
    public List<PoemContent> GetSysRecomPoemContent(Date startDate, Date endDate, int startIndex, int pageSize) {
        LambdaQueryWrapper<PoemContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.le(BaseEntity::getCreateDat,endDate)
                .ge(BaseEntity::getCreateDat,startDate)
                .le(BaseEntity::getIsDel,0)
                .orderByDesc(PoemContent::getLikes)
                .select(PoemContent::getNo,PoemContent::getTitle,PoemContent::getBrand,PoemContent::getLikes,PoemContent::getContent,BaseEntity::getCreateDat)
                .last("limit "+startIndex+","+pageSize);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public ResultSet<PoemContent> GetFinRecomPoemContent(Date date, int startIndex, int pageSize) {
        PageHelper.offsetPage(startIndex,pageSize,true);
        QueryWrapper<PoemContent> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .le(BaseEntity::getCreateDat,date)
                .eq(BaseEntity::getIsDel,0)
                .orderByDesc(PoemContent::getLikes)
                .select(PoemContent::getNo,PoemContent::getBrand,PoemContent::getTitle,PoemContent::getContent,PoemContent::getCreators,BaseEntity::getCreateBy);
       List<PoemContent> list = this.baseMapper.selectList(wrapper);
       PageInfo<PoemContent> pageInfo = new PageInfo<>(list);
       ResultSet<PoemContent> resultSet = new ResultSet<>();
       resultSet.setEntity(list.get(0));
       resultSet.setCount(pageInfo.getTotal());
       return resultSet;
    }
}

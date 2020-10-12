package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enitity.product.PaintContent;
import com.myIsoland.enitity.product.PaintingPart;
import com.myIsoland.mapper.product.PaintContentMapper;
import com.myIsoland.model.ResultSet;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PaintContentServiceImpl extends ServiceImpl<PaintContentMapper,PaintContent> implements PaintContentService{
    @Override
    public List<PaintContent> GetPaintContent(int partId, int start) {
        return this.baseMapper.selectPaintContent(partId,start);
    }

    @Override
    public int UpdatePaintContent(PaintContent data) {
        return this.baseMapper.updateById(data);
    }

    @Override
    public int DelUserPaintContent(String userId, String no) {
        LambdaQueryWrapper<PaintContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PaintContent::getNo,no)
                .eq(PaintContent::getCreateBy,userId)
                .eq(PaintContent::getIsDel,0);
        return this.baseMapper.delete(wrapper);
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
    public PaintContent GetPaintContentById(String no,String userId) {
        return this.baseMapper.selectPaintContentRecom(no);
    }

    @Override
    public PaintContent GetAdoptContent(Long partId) {
        LambdaQueryWrapper<PaintContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PaintContent::getPartId,partId)
                .eq(PaintContent::getAdopt,1)
                .eq(BaseEntity::getIsDel,0)
                .orderByDesc(BaseEntity::getCreateDat)
                .last("limit 1");
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<PaintContent> GetHotContent(Long partId,int startIndex,int pageSize) {
        QueryWrapper<PaintContent> wrapper = new QueryWrapper<>();
        wrapper.select("no","title","content","image","likes","recm_no","adopt","create_by","create_dat"/*,"instr(favorer,"+userId+") as is_like"*/)
                .lambda()
                .eq(PaintContent::getPaintId,partId)
                .ge(PaintContent::getRecomNo,40)
                .ge(PaintContent::getLikes,100)
                .eq(PaintContent::getAdopt,0)
                .eq(PaintContent::getIsDel,0)
                .orderByDesc(PaintContent::getRecomNo,PaintContent::getLikes)
                .last("LIMIT "+startIndex+","+pageSize);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public PaintContent GetUserAdvancePaintContent(String userId, int recomNo, int likes) {
        LambdaQueryWrapper<PaintContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PaintContent::getCreateBy,userId)
                .ge(PaintContent::getRecomNo,recomNo)
                .ge(PaintContent::getLikes,likes)
                .eq(PaintContent::getIsDel,0)
                .orderByDesc(PaintContent::getCreateDat)
                .select(PaintContent::getNo,PaintContent::getTitle,PaintContent::getContent,PaintContent::getImage,PaintContent::getPaintName,PaintContent::getPartName,
                        PaintContent::getLikes,PaintContent::getRecomNo,PaintContent::getCreateDat)
                .last("limit 1");
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public ResultSet<PaintContent> GetUserPaintContentByDate(String userId, Date date, int start,int limit) {
        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<PaintContent> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PaintContent::getCreateBy,userId)
                .le(PaintContent::getCreateDat,date)
                .eq(PaintContent::getIsDel,0)
                .orderByDesc(PaintContent::getCreateDat)
                .select(PaintContent::getNo,PaintContent::getTitle,PaintContent::getContent,PaintContent::getImage,PaintContent::getPaintName,PaintContent::getPartName,
                        PaintContent::getLikes,PaintContent::getPaintId,PaintContent::getSecName,PaintContent::getCreateDat);
        List<PaintContent> list = this.baseMapper.selectList(wrapper);
        PageInfo<PaintContent> pageInfo = new PageInfo<>(list);
        ResultSet<PaintContent> resultSet = new ResultSet<>();
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return  resultSet;
    }

    @Override
    public List<PaintContent> GetSysRecomPaintContent(Date startDate, Date endDate, int startIndex, int pages) {
        LambdaQueryWrapper<PaintContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.le(BaseEntity::getCreateDat,endDate)
                .ge(BaseEntity::getCreateDat,startDate)
                .le(BaseEntity::getIsDel,0)
                .orderByDesc(PaintContent::getLikes)
                .select(PaintContent::getNo,PaintContent::getPartId,PaintContent::getTitle,PaintContent::getImage,BaseEntity::getCreateDat)
                .last("limit "+startIndex+","+pages);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public ResultSet<PaintContent> GetFinRecomPaintContent(Date date, int startIndex, int pageSize) {
        PageHelper.offsetPage(startIndex,pageSize,true);
        QueryWrapper<PaintContent> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .le(BaseEntity::getCreateDat,date)
                .eq(BaseEntity::getIsDel,0)
                .orderByDesc(PaintContent::getLikes)
                .select(PaintContent::getNo,PaintContent::getImage,PaintContent::getTitle,PaintContent::getContent,PaintContent::getLikes,PaintContent::getPartId,PaintContent::getCreators,BaseEntity::getCreateBy,BaseEntity::getCreateDat);
        List<PaintContent> list = this.baseMapper.selectList(wrapper);
        PageInfo<PaintContent> pageInfo = new PageInfo<>(list);
        ResultSet<PaintContent> resultSet = new ResultSet<>();
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return resultSet;
    }

    @Override
    public ResultSet<PaintContent> GetContentsOrderByDate(String userId,Long partId, Date date, int startIndex,int pageSize,List<String> arr) {
        PageHelper.offsetPage(startIndex,pageSize,true);
        QueryWrapper<PaintContent> wrapper = new QueryWrapper<>();
        wrapper.select("no","title","content","image","likes","recm_no as recomNo","part_name as partName","sec_name as secName","adopt","create_by as createBy","create_dat as createDat"/*,"instr(favorer,"+userId+") as is_like"*/)
                .lambda()
                .eq(PaintContent::getPaintId,partId)
                .lt(PaintContent::getCreateDat,date)
                .eq(PaintContent::getIsDel,0)
                .notIn(PaintContent::getNo,arr)
                .orderByDesc(PaintContent::getCreateDat)
                .last("LIMIT "+startIndex+","+pageSize);
        List<PaintContent> list = this.baseMapper.selectList(wrapper);
        ResultSet<PaintContent> resultSet = new ResultSet<>();

        PageInfo<PaintContent> pageInfo = new PageInfo<>(list);

        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return resultSet;
    }

    /**
     * 根据热度获取创作内容
     * @param userId
     * @param partId
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Override
    public ResultSet<PaintContent> GetContentsByLikes(String userId,Long partId, int startIndex,int pageSize) {
        PageHelper.offsetPage(startIndex,pageSize,true);
        QueryWrapper<PaintContent> wrapper = new QueryWrapper<>();
        wrapper.select("no","title","content","image","likes","recm_no as recomNo","part_name as partName","sec_name as secName","adopt","create_by as createBy","create_dat as createDat"/*,"instr(favorer,"+userId+") as is_like"*/)
                .lambda()
                .eq(PaintContent::getPaintId,partId)
                .ge(PaintContent::getLikes,40)
                .ge(PaintContent::getCreators,10)
                .eq(PaintContent::getIsDel,0)
                .orderByDesc(PaintContent::getCreateDat)
                .last("LIMIT "+startIndex+","+pageSize);
        List<PaintContent> list = this.baseMapper.selectList(wrapper);
        ResultSet<PaintContent> resultSet = new ResultSet<>();

        PageInfo<PaintContent> pageInfo = new PageInfo<>(list);

        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return resultSet;
    }
}

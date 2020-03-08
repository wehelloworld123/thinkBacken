package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.product.PaintContent;
import com.myIsoland.enitity.product.PaintingPart;
import com.myIsoland.mapper.product.PaintContentMapper;
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
    public PaintContent GetPaintContentById(String no) {
        return this.baseMapper.selectPaintContentRecom(no);
    }

    @Override
    public List<PaintContent> GetHotContent(String userId,Long partId) {
        QueryWrapper<PaintContent> wrapper = new QueryWrapper<>();
        wrapper.select("no","title","image","image","likes","recm_no","adopt","create_by","create_dat","instr(favorer,"+userId+") as is_like")
                .lambda()
                .eq(PaintContent::getPaintId,partId)
                .ge(PaintContent::getRecomNo,40)
                .ge(PaintContent::getLikes,100)
                .eq(PaintContent::getAdopt,0)
                .eq(PaintContent::getIsDel,0)
                .orderByDesc(PaintContent::getRecomNo,PaintContent::getLikes)
                .last("LIMIT 8");
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
                .select(PaintContent::getNo,PaintContent::getTitle,PaintContent::getImage,PaintContent::getPaintName,PaintContent::getPartName,
                        PaintContent::getLikes,PaintContent::getRecomNo,PaintContent::getCreateDat)
                .last("limit 1");
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<PaintContent> GetUserPaintContentByDate(String userId, Date date, int page) {
        LambdaQueryWrapper<PaintContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PaintContent::getCreateBy,userId)
                .ge(PaintContent::getCreateDat,date)
                .eq(PaintContent::getIsDel,0)
                .orderByDesc(PaintContent::getCreateDat)
                .select(PaintContent::getNo,PaintContent::getTitle,PaintContent::getImage,PaintContent::getPaintName,PaintContent::getPartName,
                        PaintContent::getLikes,PaintContent::getRecomNo,PaintContent::getCreateDat)
                .last("Limit "+page+",20");
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<PaintContent> GetContentsOrderByDate(String userId,Long partId, Date date, List<String> arr) {
        QueryWrapper<PaintContent> wrapper = new QueryWrapper<>();
        wrapper.select("no","title","image","image","likes","recm_no","adopt","create_by","create_dat","instr(favorer,"+userId+") as is_like")
                .lambda()
                .eq(PaintContent::getPaintId,partId)
                .lt(PaintContent::getCreateDat,date)
                .eq(PaintContent::getIsDel,0)
                .notIn(PaintContent::getNo,arr)
                .orderByDesc(PaintContent::getCreateDat)
                .last("LIMIT 15");
        return this.baseMapper.selectList(wrapper);
    }
}

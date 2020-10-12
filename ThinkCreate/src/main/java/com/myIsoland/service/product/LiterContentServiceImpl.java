package com.myIsoland.service.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.product.LiterContent;
import com.myIsoland.mapper.product.LiterContentMapper;
import com.myIsoland.model.ResultSet;
import com.myIsoland.shiro.util.ShiroUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class LiterContentServiceImpl extends ServiceImpl<LiterContentMapper,LiterContent> implements LiterContentService {
    @Override
    public List<LiterContent> GetLiterContent(Long charpId, int start) {
        return this.baseMapper.selectLiterContent(charpId,start);
    }

    @Override
    public int SaveLiterContent(LiterContent data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public int UpdateLiterContent(LiterContent data) {
        data.setNo(ProjectConstant.CLITERPREFIX+data.getNo());
        return this.baseMapper.updateById(data);
    }

    @Override
    public int UpdateLikeSts(String userId, String no) {
        userId = userId + ";";
        return this.baseMapper.updateLikeSts(userId,no);
    }
    @Override
    public int batchUpdateLikes(List<LiterContent> data) {
        return this.baseMapper.batchUpdateLikeSts(data);
    }
    @Override
    public int DelLikeSts(String userId, String no) {
        userId = userId + ";";
        return this.baseMapper.delLikeSts(userId,no);
    }


    @Override
    public int DeleteUserLiterContent(String userId, String no) {
        LambdaQueryWrapper<LiterContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LiterContent::getNo,no)
                .eq(LiterContent::getCreateBy,userId)
                .eq(LiterContent::getIsDel,0);
        return this.baseMapper.delete(wrapper);
    }

    @Override
    public List<LiterContent> GetUserLiterContent(String userId, int start) {

        return this.baseMapper.selectUserliterCont(userId,start);
    }

    @Override
    public LiterContent GetTopLiterContent(String userId) {
        return this.baseMapper.selectTopliterCont(userId);
    }

    @Override
    public List<LiterContent> GetHotContent(Long charpId) {
        QueryWrapper<LiterContent> wrapper = new QueryWrapper<>();
        /*wrapper.select("no","title","summary","likes","recom_no","adopt","create_by","create_dat","instr(favorer,"+userId+") as is_like")*/
        wrapper.select("no","title","summary","likes","recom_no","adopt","create_by","create_dat")
                .lambda().eq(LiterContent::getCharpId,charpId)
                .ge(LiterContent::getRecomNo,40)
                .ge(LiterContent::getLikes,100)
                .eq(LiterContent::getAdopt,0)
                .eq(LiterContent::getIsDel,0)
                .orderByDesc(LiterContent::getRecomNo,LiterContent::getLikes)
                .last("LIMIT 8");
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public ResultSet<LiterContent> GetContentsOrderByDate(Long charpId, Date date,int start,int limit, List<String> arr) {
        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<LiterContent> wrapper = new QueryWrapper<>();
        wrapper.select("no","title","summary","likes","recom_no","adopt","create_by","create_dat")
                .lambda()
                .eq(LiterContent::getCharpId,charpId)
                .lt(LiterContent::getCreateDat,date)
                .eq(LiterContent::getIsDel,0)
                .notIn(LiterContent::getNo,arr)
                .orderByDesc(LiterContent::getCreateDat);
        List<LiterContent> list = this.baseMapper.selectList(wrapper);
        PageInfo<LiterContent> pageInfo = new PageInfo<>(list);
        ResultSet<LiterContent> resultSet = new ResultSet<>();
        resultSet.setCount(pageInfo.getTotal());
        resultSet.setList(list);
        return resultSet;
    }
    @Override
    public ResultSet<LiterContent> GetContentsOrderByFavors(Long charpId, int likes,int start,int limit) {
        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<LiterContent> wrapper = new QueryWrapper<>();
        wrapper.select("no","title","summary","likes","recom_no","adopt","create_by","create_dat")
                .lambda()
                .eq(LiterContent::getCharpId,charpId)
                .ge(LiterContent::getLikes,likes)
                .eq(LiterContent::getIsDel,0)
                .orderByDesc(LiterContent::getCreateDat);
        List<LiterContent> list = this.baseMapper.selectList(wrapper);
        PageInfo<LiterContent> pageInfo = new PageInfo<>(list);
        ResultSet<LiterContent> resultSet = new ResultSet<>();
        resultSet.setCount(pageInfo.getTotal());
        resultSet.setList(list);
        return resultSet;
    }

    @Override
    public LiterContent GetLiterContentDetail(String userid,String no) {
        return this.baseMapper.selectLiterContentRecom(userid,no);
    }

    @Override
    public LiterContent GetAdoptContent(Long chaptId) {
        LambdaQueryWrapper<LiterContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LiterContent::getCharpId,chaptId)
                .eq(LiterContent::getAdopt,1)
                .eq(BaseEntity::getIsDel,0)
                .orderByDesc(BaseEntity::getCreateDat)
                .last("limit 1");
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public LiterContent GetUserAdvanceLiterContent(String userId,int recomNo,int likes) {
        LambdaQueryWrapper<LiterContent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LiterContent::getCreateBy,userId)
                .ge(LiterContent::getRecomNo,recomNo)
                .ge(LiterContent::getLikes,likes)
                .eq(LiterContent::getIsDel,0)
                .orderByDesc(LiterContent::getCreateDat)
                .select(LiterContent::getNo,LiterContent::getTitle,LiterContent::getSummary,LiterContent::getBookName,LiterContent::getCharpName,
                        LiterContent::getCreateDat)
                .last("limit 1");
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public ResultSet<LiterContent> GetUserLiterContentByDate(String userId, Date date,int start,int limit) {
        PageHelper.offsetPage(start,limit,true);
        QueryWrapper<LiterContent> wrapper = new QueryWrapper<>();
        wrapper.select("no","title","substring(content,1,200) as content","charp_id as charpId","charp_name as charpName","sec_name as secName","create_dat as createDat")
                .lambda().eq(LiterContent::getCreateBy,userId)
                .le(LiterContent::getCreateDat,date)
                .eq(LiterContent::getIsDel,0)
                .orderByDesc(LiterContent::getCreateDat);
        List<LiterContent> list = this.baseMapper.selectList(wrapper);
        PageInfo<LiterContent> pageInfo = new PageInfo<>(list);
        ResultSet<LiterContent> resultSet = new ResultSet<>();
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return  resultSet;
    }

    @Override
    public List<LiterContent> GetSysRecomLiterContent(Date startDate, Date endDate, int startIndex, int pageSize) {
        QueryWrapper<LiterContent> wrapper = new QueryWrapper<>();
        wrapper.select("no","title","substring(content,1,200) as content","charp_id as charpId","likes","creator","create_dat as createDat")
                .lambda()
                .le(BaseEntity::getCreateDat,endDate)
                .ge(BaseEntity::getCreateDat,startDate)
                .le(BaseEntity::getIsDel,0)
                .orderByDesc(LiterContent::getLikes)
                .last("limit "+startIndex+","+pageSize);
        return this.baseMapper.selectList(wrapper);

    }


}
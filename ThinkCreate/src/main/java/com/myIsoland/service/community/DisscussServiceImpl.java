package com.myIsoland.service.community;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.util.ImageUtils;
import com.myIsoland.common.util.StringUtils;
import com.myIsoland.enitity.community.Comment;
import com.myIsoland.enitity.community.Disscuss;
import com.myIsoland.mapper.community.DisscussMapper;
import com.myIsoland.model.ImageInfo;
import com.myIsoland.model.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DisscussServiceImpl extends ServiceImpl<DisscussMapper,Disscuss> implements DisscussService {

    @Autowired
    private CommentService commentService;
    @Override
    public List<Disscuss> GetHotDisscussByLike(String userId, int likes, Date update, int number) {
        return this.baseMapper.queryHotDiscussByLike(userId,likes,update,number);
    }


    @Override
    public ResultSet<Disscuss> GetNewDateDisscuss(String userId, Date date, int start, int number,String source) {
        PageHelper.offsetPage(start,number,true);
        List<Disscuss> list = this.baseMapper.queryNewDiscussOrderByDate(userId,date,source);
        if(list!=null&&list.size()>0){
            for(Disscuss disscuss:list){
                disscuss.setImgs(new ArrayList<>());
                if(!StringUtils.isEmpty(disscuss.getPic())){
                    File file = new File(disscuss.getPic());
                    int height = ImageUtils.getImgHeight(file);
                    int width = ImageUtils.getImgWidth(file);
                    disscuss.getImgs().add(new ImageInfo(1,disscuss.getPic(),height,width));

                }
                if(!StringUtils.isEmpty(disscuss.getPic1())){
                    File file = new File(disscuss.getPic());
                    int height = ImageUtils.getImgHeight(file);
                    int width = ImageUtils.getImgWidth(file);
                    disscuss.getImgs().add(new ImageInfo(2,disscuss.getPic1(),height,width));
                }
                if(!StringUtils.isEmpty(disscuss.getPic2())){
                    File file = new File(disscuss.getPic());
                    int height = ImageUtils.getImgHeight(file);
                    int width = ImageUtils.getImgWidth(file);
                    disscuss.getImgs().add(new ImageInfo(3,disscuss.getPic2(),height,width));
                }
                if(!StringUtils.isEmpty(disscuss.getPic3())){
                    File file = new File(disscuss.getPic());
                    int height = ImageUtils.getImgHeight(file);
                    int width = ImageUtils.getImgWidth(file);
                    disscuss.getImgs().add(new ImageInfo(4,disscuss.getPic3(),height,width));
                }
                if(!StringUtils.isEmpty(disscuss.getPic4())){
                    File file = new File(disscuss.getPic());
                    int height = ImageUtils.getImgHeight(file);
                    int width = ImageUtils.getImgWidth(file);
                    disscuss.getImgs().add(new ImageInfo(5,disscuss.getPic4(),height,width));
                }
                if(!StringUtils.isEmpty(disscuss.getPic5())){
                    File file = new File(disscuss.getPic());
                    int height = ImageUtils.getImgHeight(file);
                    int width = ImageUtils.getImgWidth(file);
                    disscuss.getImgs().add(new ImageInfo(6,disscuss.getPic5(),height,width));
                }
                if(!StringUtils.isEmpty(disscuss.getPic6())){
                    File file = new File(disscuss.getPic());
                    int height = ImageUtils.getImgHeight(file);
                    int width = ImageUtils.getImgWidth(file);
                    disscuss.getImgs().add(new ImageInfo(7,disscuss.getPic6(),height,width));
                }
                if(!StringUtils.isEmpty(disscuss.getPic7())){
                    File file = new File(disscuss.getPic());
                    int height = ImageUtils.getImgHeight(file);
                    int width = ImageUtils.getImgWidth(file);
                    disscuss.getImgs().add(new ImageInfo(8,disscuss.getPic7(),height,width));
                }
                if(!StringUtils.isEmpty(disscuss.getPic8())){
                    File file = new File(disscuss.getPic());
                    int height = ImageUtils.getImgHeight(file);
                    int width = ImageUtils.getImgWidth(file);
                    disscuss.getImgs().add(new ImageInfo(9,disscuss.getPic8(),height,width));
                }
               ResultSet<Comment> resultSet = commentService.GetCommentByDate(userId,disscuss.getId(),date,0,2);
               disscuss.setComments(resultSet.getList());
               disscuss.setCommentNo(Integer.valueOf(resultSet.getCount()+""));
            }
        }
        PageInfo<Disscuss> pageInfo = new PageInfo<>(list);
        ResultSet<Disscuss> resultSet = new ResultSet<>();

        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());
        return resultSet;
    }

    @Override
    public ResultSet<Disscuss> GetConcernDis(String userId, Date date, int start, int number,String source) {
        ResultSet<Disscuss> resultSet = new ResultSet<>();
        List<Disscuss> list = this.baseMapper.queryConcernDisById(userId,date,start,number,source);
        resultSet.setList(list);
        return  resultSet;
    }

    @Override
    public Disscuss GetDiscussInfo(Long id, String userId, Date date) {
        return this.baseMapper.queryDisccussInfo(id,userId,date);
    }

    @Override
    public int updateLikeSts(String userId, Long id) {
        userId = userId + ";";
        return this.baseMapper.updateLikeSts(userId,id);
    }

    @Override
    public int batchUpdateLikes(List<Disscuss> data) {
        return this.baseMapper.batchUpdateLikeSts(data);
    }

    @Override
    public int delLikeSts(String userId, Long id) {
        userId = userId + ";";
        return this.baseMapper.delLikeSts(userId,id);
    }


    @Override
    public int SaveDisscuss(Disscuss data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public List<Disscuss> GetDiscussById(String userId) {
        LambdaQueryWrapper<Disscuss> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Disscuss::getCreateBy,userId)
                .eq(Disscuss::getIsDel,0)
                .orderByDesc(Disscuss::getCreateDat)
                .last("limit 20");
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<Disscuss> GetUserDiscuss(String userId, Date date, int page) {
        page = page * 20;
        LambdaQueryWrapper<Disscuss> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Disscuss::getCreateBy,userId)
                .le(Disscuss::getCreateDat,date)
                .eq(Disscuss::getIsDel,0)
                .orderByDesc(Disscuss::getCreateDat)
                .last("limit "+page+", 20");
        return this.baseMapper.selectList(wrapper);
    }
}

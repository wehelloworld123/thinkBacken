package com.myIsoland.service.debate;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.common.util.StringUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.debate.Answer;
import com.myIsoland.enitity.debate.Recommend;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.mapper.debate.AnswerMapper;
import com.myIsoland.model.ResultSet;
import com.myIsoland.shiro.util.ShiroUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper,Answer> implements AnswerService {


    @Override
    public int delLikeSts(String ansId, String userId) {
        return this.baseMapper.delLikeSts(userId,ansId);
    }

    @Override
    public int addLikeSts(String ansId, String userId) {
        return this.baseMapper.updateLikeSts(userId,ansId);
    }

    @Override
    public List<Answer> GetLastThrCapter(String topicId) {
        return null;
    }


    /**
     * 根据日期获取最新的用户立论排序
     * @param topicId
     * @param date
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<Answer> GetAnswersByDate(String topicId, Date date, int start,int limit) {
  /*      PageHelper.offsetPage(start,limit,true);
        QueryWrapper<Answer> wrapper = new QueryWrapper<>();

        wrapper.select("no","substring(content,1,300) as content","likes","recommend","topic_id as topicId","creator","creator_avat as creatorAvat","createBy")
                .lambda().eq(Answer::getTopicId,topicId)
                .le(Answer::getCreateDat,date)
                .eq(Answer::getIsDel,0)
                .orderByDesc(Answer::getCreateDat);
        List<Answer> list = this.baseMapper.selectList(wrapper);
        PageInfo<Answer> pageInfo = new PageInfo<>(list);
        ResultSet<Answer> resultSet = new ResultSet<>();
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());*/
        return  this.baseMapper.queryAnsAndRecByDate(topicId,date,start,limit);
    }

    /**
     * 根据topicid获取
     * @param topicId
     * @param date
     * @param likes
     * @param recoms
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<Answer> GetAdvanceAnsByTopicId(String topicId,Date date,int likes,int recoms,int start,int limit) {
       /* PageHelper.offsetPage(start,limit,true);
        QueryWrapper<Answer> wrapper = new QueryWrapper<>();

        wrapper.select("no","substring(content,1,300) as content","likes","recommend","topic_id as topicId","creator","creator_avat as creatorAvat","createBy")
                .lambda().eq(Answer::getTopicId,topicId)
                .le(Answer::getCreateDat,date)
                .eq(Answer::getIsDel,0)
                .orderByDesc(Answer::getCreateDat);
        List<Answer> list = this.baseMapper.selectList(wrapper);
        PageInfo<Answer> pageInfo = new PageInfo<>(list);
        ResultSet<Answer> resultSet = new ResultSet<>();
        resultSet.setList(list);
        resultSet.setCount(pageInfo.getTotal());*/
        return  this.baseMapper.queryHotAnsAndRecByTopicId(topicId,date,likes,recoms,start,limit);
    }

    @Override
    public List<Answer> GetNextAnswer(String topicId, Date date) {
        LambdaQueryWrapper<Answer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Answer::getTopicId,topicId)
                .gt(Answer::getCreateDat,date)
                .orderByDesc(Answer::getCreateDat)
                .last("LIMIT 10")
                .select(Answer::getNo,Answer::getConclusion,Answer::getLikes,Answer::getRecommend,
                        Answer::getCreator,Answer::getCreatorAvat,Answer::getCreateDat,Answer::getCreateBy);
        return deleteAnsListPrefix(this.baseMapper.selectList(wrapper));
    }

    @Override
    public List<Answer> GetNextAdvanAns(String topicId, Date date) {
        LambdaQueryWrapper<Answer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Answer::getTopicId,topicId)
                .gt(Answer::getLikes,100)
                .gt(Answer::getRecommend,40)
                .gt(Answer::getCreateDat,date)
                .orderByDesc(Answer::getGrade)
                .last("LIMIT 10")
                .select(Answer::getNo,Answer::getConclusion,Answer::getLikes,Answer::getRecommend,
                        Answer::getCreator,Answer::getCreatorAvat,Answer::getCreateDat,Answer::getCreateBy);
        List<Answer> data = this.baseMapper.selectList(wrapper);
        return deleteAnsListPrefix(data);
    }

    @Override
    public Answer GetAnswerById(String no,String userId) {
        QueryWrapper<Answer> wrapper = new QueryWrapper<>();
        wrapper.select("no","content","conclusion","likes","creator","creator_avat as creatorAvat","instr(favorer,"+userId+") as islike","recommend","createBy","create_dat as createDat")
                .lambda()
                .eq(Answer::getNo,no)
                .eq(BaseEntity::getIsDel,0);
        Answer data = this.baseMapper.selectOne(wrapper);
        data.setNo(CaculateUtils.translateId(ProjectConstant.ANSPREFIX,data.getNo()));
        return deletePrefix(data);
    }

    @Override
    public int InsertAnswer(Answer data) {
        String no = ProjectConstant.ANSPREFIX + SnowflakeIdWorker.getUUID();
        data.setNo(no);
        TsysUser user = ShiroUtils.getUser();
        data.setCreator(user.getUsername());
        data.setCreatorAvat(user.getAvatar());
        return this.baseMapper.insert(data);
    }

    @Override
    public int DeleteAns(String id) {
        id = ProjectConstant.ANSPREFIX + id;
        return this.baseMapper.deleteById(id);
    }


    private Answer deletePrefix(Answer data){

        data.setNo(CaculateUtils.translateId(ProjectConstant.ANSPREFIX,data.getNo()));
        data.setTopicId(CaculateUtils.translateId(ProjectConstant.TOPICPREFIX,data.getTopicId()));
        data.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,data.getCreateBy()));
        return data;
    }
    private List<Answer> deleteAnsListPrefix(List<Answer> data){
        for(Answer item :data){
            item.setNo(CaculateUtils.translateId(ProjectConstant.ANSPREFIX,item.getNo()));
            item.setTopicId(CaculateUtils.translateId(ProjectConstant.TOPICPREFIX,item.getTopicId()));
            item.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX,item.getCreateBy()));
            if(!item.getRecommends().isEmpty()) {
                for (Recommend i : item.getRecommends()) {
                    i.setCreateBy(CaculateUtils.translateId(ProjectConstant.USERPREFIX, item.getCreateBy()));
                }
            }
        }
        return data;
    }
}

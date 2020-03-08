package com.myIsoland.service.debate;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.common.util.CaculateUtils;
import com.myIsoland.common.util.SnowflakeIdWorker;
import com.myIsoland.common.util.StringUtils;
import com.myIsoland.constant.ProjectConstant;
import com.myIsoland.enitity.debate.Answer;
import com.myIsoland.enitity.debate.Recommend;
import com.myIsoland.enitity.system.TsysUser;
import com.myIsoland.mapper.debate.AnswerMapper;
import com.myIsoland.shiro.util.ShiroUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper,Answer> implements AnswerService {
    @Override
    public int AddAnsLikes(int ansId) {
        return this.baseMapper.updateAnsLike(ansId);
    }

    @Override
    public List<Answer> GetLastThrCapter(String topicId) {
        return null;
    }

    @Override
    public List<Answer> GetNewAnsById(String topicId, Date date, int start) {
        return deleteAnsListPrefix(this.baseMapper.queryAnsAndRecById(topicId,date,start));
    }

    @Override
    public List<Answer> GetAdvanceAnsById(String topicId, Date date, int start) {
        return deleteAnsListPrefix(this.baseMapper.queryHotAnsAndRecById(topicId,date,start));
    }

    @Override
    public List<Answer> GetNextAnswer(String topicId, Date date) {
        LambdaQueryWrapper<Answer> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Answer::getTopicId,topicId)
                .gt(Answer::getCreateDat,date)
                .orderByDesc(Answer::getCreateDat)
                .last("LIMIT 10")
                .select(Answer::getNo,Answer::getSummary,Answer::getLikes,Answer::getRecommend,
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
                .select(Answer::getNo,Answer::getSummary,Answer::getLikes,Answer::getRecommend,
                        Answer::getCreator,Answer::getCreatorAvat,Answer::getCreateDat,Answer::getCreateBy);
        List<Answer> data = this.baseMapper.selectList(wrapper);
        return deleteAnsListPrefix(data);
    }

    @Override
    public Answer GetAnswerById(String no) {
        Answer data = this.baseMapper.selectById(no);
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

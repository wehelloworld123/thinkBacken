package com.myIsoland.service.evaluate;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.annotation.DataSource;
import com.myIsoland.enitity.evaluate.Evaluation;
import com.myIsoland.enums.DataSourceEnum;
import com.myIsoland.mapper.evalute.EvalutionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.NESTED,isolation = Isolation.DEFAULT,readOnly = false,rollbackFor = Exception.class)
public class EvaluationServiceImpl extends ServiceImpl<EvalutionMapper,Evaluation> implements EvaluationService {
    @Override
    public int SaveEvaluation(Evaluation data) {
        return this.baseMapper.insert(data);
    }

    @Override
    public Evaluation GetEvaluation(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public int DelEvaluation(String uid) {
        return this.baseMapper.deleteById(uid);
    }
}

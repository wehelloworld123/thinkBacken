package com.myIsoland.service.evaluate;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.evaluate.Subject;
import com.myIsoland.mapper.evalute.SubjectMapper;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper,Subject> implements SubjectService {
}

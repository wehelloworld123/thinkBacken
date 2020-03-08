package com.myIsoland.service.personInfo;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.common.base.BaseService;
import com.myIsoland.enitity.personInfo.PersonInfo;
import com.myIsoland.mapper.personInfo.PersonInfoMapper;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class PersonInfoServiceImpl extends ServiceImpl<PersonInfoMapper,PersonInfo> implements PersonInfoService {

    @Override
    public PersonInfo GetPersonInfo(String userId) {
        return this.baseMapper.selectPersonInfo(userId);
    }
}

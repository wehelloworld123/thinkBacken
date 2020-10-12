package com.myIsoland.service.consumption;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myIsoland.enitity.consumption.Account;
import com.myIsoland.mapper.consumption.AccountMapper;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper,Account> implements AccountService {
}

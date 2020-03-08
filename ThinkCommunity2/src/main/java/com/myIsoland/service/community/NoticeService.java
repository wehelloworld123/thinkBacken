package com.myIsoland.service.community;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.community.Notice;
import org.springframework.stereotype.Service;


public interface NoticeService extends IService<Notice> {
    Notice GetCurrentNotice(String corpId);
}

package com.myIsoland.service.community;

import com.baomidou.mybatisplus.extension.service.IService;
import com.myIsoland.enitity.community.Notice;


public interface NoticeService extends IService<Notice> {
    Notice GetCurrentNotice(String corpId);
}

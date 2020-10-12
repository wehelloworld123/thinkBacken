package com.myIsoland.domain;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LoadCreatDataScheduleTask {

    /**
     * 加载文学创作信息到内存
     */
    @Scheduled(cron = "0 0 3 * * ?") //每5分钟
    public void loadLiteratureData() {

    }
}

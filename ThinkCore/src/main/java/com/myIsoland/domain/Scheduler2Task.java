package com.myIsoland.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler2Task {
    private final Logger logger = LoggerFactory.getLogger(Scheduler2Task.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(("HH:mm:ss"));
    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime(){
        logger.info("现在时间："+dateFormat.format(new Date()));
    }
}

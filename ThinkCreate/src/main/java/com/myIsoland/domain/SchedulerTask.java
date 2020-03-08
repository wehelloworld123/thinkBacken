package com.myIsoland.domain;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


public class SchedulerTask {

    private int count=0;

    @Scheduled(cron = "0 0 0/30 0 *")
    private void process(){
        System.out.println("this is scheduler task runing" + (count++));
    }
}

package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.myIsoland.mapper")
@EnableScheduling
public class SpringbootStart {
;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootStart.class, args);
	}

}

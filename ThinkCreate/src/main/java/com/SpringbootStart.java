package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.myIsoland.mapper")
public class SpringbootStart {
;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootStart.class, args);
	}

}

package com.finaldesign.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.finaldesign.dao.mapper")
@ComponentScan("com.finaldesign")
@SpringBootApplication
public class Finaldesign20200418Application {

	public static void main(String[] args) {
		SpringApplication.run(Finaldesign20200418Application.class, args);
	}

}

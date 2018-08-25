package com.sqq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// 扫描mybatis mapper包路径
@MapperScan(basePackages="com.sqq.mapper")
// 扫描所有需要的包，包含一些自用工具类包所在的路径
@ComponentScan(basePackages={"com.sqq"})
public class SqqSpringbootStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqqSpringbootStarterApplication.class, args);
	}
}

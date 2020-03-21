package com.sqq;

import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// 扫描mybatis mapper包路径
@MapperScan(basePackages="com.sqq.mapper")
// 扫描所有需要的包，包含一些自用工具类包所在的路径
@ComponentScan(basePackages={"com.sqq"})
// 开启定时任务
@EnableScheduling
// 开启异步调用方法
@EnableAsync
// 读取自定义properties文件
@PropertySource(value = {"config/business-config.properties"})
public class SqqSpringbootStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqqSpringbootStarterApplication.class, args);
	}
}

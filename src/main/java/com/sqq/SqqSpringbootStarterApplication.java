package com.sqq;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

	@Bean
	public Redisson redisson(){
		Config config = new Config();
		// 此为单机模式
		/*config.useSingleServer().setAddress("redis://127.0.0.1:6381").setDatabase(0);*/

		// 集群模式
        /*config.useClusterServers()
                .addNodeAddress("redis://127.0.0.1:8001")
                .addNodeAddress("redis://127.0.0.1:8002")
                .addNodeAddress("redis://127.0.0.1:8003")
                .addNodeAddress("redis://127.0.0.1:8004")
                .addNodeAddress("redis://127.0.0.1:8005")
                .addNodeAddress("redis://127.0.0.1:8006");*/

		// 哨兵模式
		config.useSentinelServers().addSentinelAddress("127.0.0.1:26379", "127.0.0.1:26380", "127.0.0.1:26381")
				.setMasterName("mymaster").setPassword("").setDatabase(0);
		return (Redisson) Redisson.create(config);
	}

}

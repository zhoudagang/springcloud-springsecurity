package com.everyman.srpingcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhougangcom.everyman.srpingcloud.mapper
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.everyman.srpingcloud.mapper")
public class SysAdminApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SysAdminApplication.class, args);
    }
}

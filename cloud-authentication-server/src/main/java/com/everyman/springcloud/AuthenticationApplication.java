package com.everyman.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhougang
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AuthenticationApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AuthenticationApplication.class, args);
    }
}

package com.everyman.springcloud.config;

import com.everyman.springcloud.service.IResourceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhougang
 */
@Component
public class StartRunner implements CommandLineRunner
{
    @Resource
    private IResourceService resourceService;

    @Override
    public void run(String... args)
    {
        System.out.println("StartRunner.....loadResource");
        resourceService.loadResource();
    }
}

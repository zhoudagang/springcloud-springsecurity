package com.everyman.srpingcloud.controller;

import com.everyman.srpingcloud.common.Result;
import com.everyman.srpingcloud.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 资源表
 *
 * @author zhougang
 */
@RestController
@RequestMapping("/resource")
@Slf4j
public class ResourceController
{

    @Resource
    private IResourceService resourceService;

    @GetMapping(value = "/user/{username}")
    public Result queryByUsername(@PathVariable("username") String username) {
        log.debug("query with username:{}", username);
        return Result.success(resourceService.query(username));
    }

    @GetMapping(value = "/all")
    public Result queryAll()
    {
        return Result.success(resourceService.getAll());
    }
}

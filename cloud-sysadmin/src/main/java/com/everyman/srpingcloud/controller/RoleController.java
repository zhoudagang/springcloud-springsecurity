package com.everyman.srpingcloud.controller;

import com.everyman.srpingcloud.common.Result;
import com.everyman.srpingcloud.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhougang
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController
{
    @Resource
    private IRoleService roleService;

    @GetMapping(value = "/user/{userId}")
    public Result query(@PathVariable("userId") String userId)
    {
        log.debug("query with userId:{}", userId);
        return Result.success(roleService.query(userId));
    }


}

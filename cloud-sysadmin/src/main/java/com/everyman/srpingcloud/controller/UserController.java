package com.everyman.srpingcloud.controller;

import com.everyman.srpingcloud.common.Result;
import com.everyman.srpingcloud.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户表
 *
 * @author zhougang
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController
{
    @Resource
    private IUserService userService;

    @GetMapping
    public Result query(@RequestParam("uniqueId") String uniqueId)
    {
        log.debug("query with username or mobile:{}", uniqueId);
        return Result.success(userService.getByUniqueId(uniqueId));
    }
}

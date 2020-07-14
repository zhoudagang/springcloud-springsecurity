package com.everyman.springcloud.service;

import com.everyman.springcloud.entity.User;
import com.everyman.srpingcloud.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhougang
 */
@Component
@FeignClient(name = "cloud-sysadmin")
public interface IUserService
{

    /**
     * 根据用户唯一标识获取用户信息
     * 目前用户标识 用户名或mobile
     *
     * @param uniqueId 唯一标识
     * @return ResultBean
     */
    @GetMapping("/user")
    Result<User> query(@RequestParam("uniqueId") String uniqueId);
}

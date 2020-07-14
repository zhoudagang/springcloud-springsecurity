package com.everyman.springcloud.service;

import com.everyman.springcloud.entity.Role;
import com.everyman.srpingcloud.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author zhougang
 */
@Component
@FeignClient(name = "cloud-sysadmin")
public interface IRoleService
{
    /**
     * 根据用户id查询用户拥有的角色
     *
     * @param userId 用户id
     * @return List<Role>
     */
    @GetMapping(value = "/role/user/{userId}")
    Result<List<Role>> query(@PathVariable("userId") String userId);
}

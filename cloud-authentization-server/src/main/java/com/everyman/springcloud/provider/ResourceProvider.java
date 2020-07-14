package com.everyman.springcloud.provider;

import com.everyman.springcloud.entity.Resource;
import com.everyman.srpingcloud.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * @author everyman
 */
@Component
@FeignClient(name = "cloud-sysadmin", fallback = ResourceProviderFallback.class)
public interface ResourceProvider
{

    @GetMapping(value = "/resource/all")
    Result<Set<Resource>> resources();

    @GetMapping(value = "/resource/user/{username}")
    Result<Set<Resource>> resources(@PathVariable("username") String username);
}

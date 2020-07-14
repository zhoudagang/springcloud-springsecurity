package com.everyman.springcloud.provider;

import com.everyman.springcloud.entity.Resource;
import com.everyman.srpingcloud.common.Result;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * @author everyman
 */
@Component
public class ResourceProviderFallback implements ResourceProvider
{
    @Override
    public Result<Set<Resource>> resources()
    {
        return Result.fail("认证服务启动时加载资源异常！未加载到资源");
    }

    @Override
    public Result<Set<Resource>> resources(String username)
    {
        return Result.fail("认证服务查询用户异常！查询用户资源为空！");
    }
}

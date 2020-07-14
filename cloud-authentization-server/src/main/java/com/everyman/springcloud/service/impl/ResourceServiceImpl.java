package com.everyman.springcloud.service.impl;

import com.everyman.springcloud.entity.Resource;
import com.everyman.springcloud.provider.ResourceProvider;
import com.everyman.springcloud.service.IResourceService;
import com.everyman.springcloud.service.NewMvcRequestMatcher;
import com.everyman.srpingcloud.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhougang
 */
@Service
@Slf4j
public class ResourceServiceImpl implements IResourceService
{
    @Autowired
    private HandlerMappingIntrospector mvcHandlerMappingIntrospector;

    @Autowired
    private ResourceProvider resourceProvider;

    /**
     * 系统中所有权限集合
     */
    private static final Map<RequestMatcher, ConfigAttribute> RESOURCE_CONFIG_ATTRIBUTES = new HashMap<>();


    @Override
    public synchronized void saveResource(Resource resource)
    {
        RESOURCE_CONFIG_ATTRIBUTES.put(
                this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                new SecurityConfig(resource.getCode())
        );
    }

    @Override
    public synchronized void removeResource(Resource resource)
    {
        RESOURCE_CONFIG_ATTRIBUTES.remove(this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()));
        log.info("resourceConfigAttributes size:{}", RESOURCE_CONFIG_ATTRIBUTES.size());
    }

    @Override
    public synchronized void loadResource()
    {
        Result<Set<Resource>> resourcesResult = resourceProvider.resources();
        if (resourcesResult.isFail())
        {
            System.out.println(" loadResource error");
            System.exit(1);
        }
        Set<Resource> res = resourcesResult.getData();
        Map<MvcRequestMatcher, SecurityConfig> tempResourceConfigAttributes = res.stream()
                .collect(Collectors.toMap(
                        resource -> this.newMvcRequestMatcher(resource.getUrl(), resource.getMethod()),
                        resource -> new SecurityConfig(resource.getCode())
                ));
        RESOURCE_CONFIG_ATTRIBUTES.putAll(tempResourceConfigAttributes);
        log.debug("init resourceConfigAttributes:{}", RESOURCE_CONFIG_ATTRIBUTES);
    }

    @Override
    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest authRequest)
    {
        return RESOURCE_CONFIG_ATTRIBUTES.keySet().stream()
                .filter(requestMatcher -> requestMatcher.matches(authRequest))
                .map(RESOURCE_CONFIG_ATTRIBUTES::get)
                .peek(urlConfigAttribute -> log.debug("url在资源池中配置：{}", urlConfigAttribute.getAttribute()))
                .findFirst()
                .orElse(new SecurityConfig("NONEXISTENT_URL"));
    }

    @Override
    public Set<Resource> queryByUsername(String username)
    {
        return resourceProvider.resources(username).getData();
    }


    /**
     * 创建RequestMatcher
     */
    private MvcRequestMatcher newMvcRequestMatcher(String url, String method)
    {
        return new NewMvcRequestMatcher(mvcHandlerMappingIntrospector, url, method);
    }
}

package com.everyman.springcloud.service.impl;

import com.everyman.springcloud.service.IAuthenticationService;
import com.everyman.springcloud.service.IResourceService;
import com.everyman.springcloud.entity.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author zhougang
 */
@Service
@Slf4j
public class AuthenticationServiceImpl implements IAuthenticationService
{

    /**
     * 未在资源库中的URL默认标识
     */
    public static final String NONEXISTENT_URL = "NONEXISTENT_URL";

    @javax.annotation.Resource
    private IResourceService resourceService;

    /**
     * @param authRequest 访问的url,method
     * @return 有权限true, 无权限或全局资源中未找到请求url返回否
     */
    @Override
    public boolean decide(HttpServletRequest authRequest)
    {
        log.debug("正在访问的url是:{}，method:{}", authRequest.getServletPath(), authRequest.getMethod());
        //获取用户认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //获取此url，method访问对应的权限资源信息
        ConfigAttribute urlConfigAttribute = resourceService.findConfigAttributesByUrl(authRequest);
        if (NONEXISTENT_URL.equals(urlConfigAttribute.getAttribute()))
        {
            log.debug("url未在资源池中找到，拒绝访问");
        }
        //获取此访问用户所有角色拥有的权限资源
        Set<Resource> userRes = findResourcesByUsername(authentication.getName());
        //用户拥有权限资源 与 url要求的资源进行对比
        return isMatch(urlConfigAttribute, userRes);
    }

    /**
     * url对应资源与用户拥有资源进行匹配
     */
    public boolean isMatch(ConfigAttribute urlConfigAttribute, Set<Resource> userRes)
    {
        return userRes.stream().anyMatch(resource -> resource.getCode().equals(urlConfigAttribute.getAttribute()));
    }

    /**
     * 根据用户所被授予的角色，查询到用户所拥有的资源
     */
    private Set<Resource> findResourcesByUsername(String username)
    {
        //用户被授予的角色资源
        Set<Resource> res = resourceService.queryByUsername(username);
        if (log.isDebugEnabled())
        {
            log.debug("用户被授予角色的资源数量是:{}, 资源集合信息为:{}", res.size(), res);
        }
        return res;
    }
}

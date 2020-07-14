package com.everyman.springcloud.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhougang
 */
public interface IAuthenticationService
{
    /**
     * 校验权限
     *
     * @param authRequest HttpServletRequest
     * @return 是否有权限
     */
    boolean decide(HttpServletRequest authRequest);
}

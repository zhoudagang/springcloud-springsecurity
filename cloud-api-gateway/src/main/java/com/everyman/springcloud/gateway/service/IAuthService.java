package com.everyman.springcloud.gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * @author everyman
 */
public interface IAuthService
{
    /**
     * 调用签权服务，判断用户是否有权限
     */
    boolean authenticate(String authentication, String url, String method);

    /**
     * 判断url是否在忽略的范围内
     * 只要是配置中的开头，即返回true
     */
    boolean ignoreAuthentication(String url);


    /**
     * 调用签权服务，判断用户是否有权限
     */
    boolean hasPermission(String authentication, String url, String method);

    /**
     * 是否无效authentication
     */
    boolean invalidJwtAccessToken(String authentication);

    /**
     * 从认证信息中提取jwt token 对象
     *
     * @param jwtToken toke信息 header.payload.signature
     * @return Jws对象
     */
    Jws<Claims> getJwt(String jwtToken);
}

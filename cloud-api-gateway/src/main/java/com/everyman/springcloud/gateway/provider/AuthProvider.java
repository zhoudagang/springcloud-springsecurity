package com.everyman.springcloud.gateway.provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author everyman
 */
@Component
@FeignClient(name = "cloud-authentization-server", fallback = AuthProvider.AuthProviderFallback.class)
public interface AuthProvider
{
    /**
     * 调用签权服务，判断用户是否有权限
     */
    @PostMapping(value = "/oauth/permission")
    boolean decide(@RequestHeader(HttpHeaders.AUTHORIZATION) String authentication,
                   @RequestParam("url") String url,
                   @RequestParam("method") String method);

    @Component
    class AuthProviderFallback implements AuthProvider
    {
        /**
         * 降级统一返回无权限
         */
        @Override
        public boolean decide(String authentication, String url, String method)
        {
            System.err.println(" 降级统一返回无权限 ");
            System.err.println("authentication = " + authentication);
            System.err.println("url = " + url);
            System.err.println("method = " + method);
            return false;
        }
    }
}

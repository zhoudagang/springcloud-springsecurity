package com.everyman.springcloud.controller;

import com.everyman.springcloud.service.IAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhougang
 */
@RestController
@Slf4j
public class AuthentizationController
{
    @Resource
    private IAuthenticationService authenticationService;

    @PostMapping(value = "/oauth/permission")
    public boolean decide(@RequestParam("url") String url, @RequestParam("method") String method,
                          HttpServletRequest request)
    {
        boolean decide = authenticationService.decide(new HttpServletRequestAuthWrapper(request, url, method));
        return decide;
    }
}

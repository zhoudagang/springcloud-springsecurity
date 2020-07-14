package com.everyman.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collections;

/**
 * @author zhougang
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter
{
    @Resource
    private TokenStore tokenStore;

    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private AuthorizationCodeServices authorizationCodeServices;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtAccessTokenConverter accessTokenConverter;

    @Resource
    private PasswordEncoder passwordEncoder;


    /**
     *
     * 授权服务配置总结:授权服务配置分成三大块，可以关联记忆。 既然要完成认证，它首先得知道客户端信息从哪儿读取，因此要进行客户端详情配置。
     * 既然要颁发token，那必须得定义token的相关endpoint，以及token如何存取，以及客户端支持哪些类型的 token。
     * 既然暴露除了一些endpoint，那对这些endpoint可以定义一些安全上的约束等。
     *
     */

    /**
     * 客户端详情相关配置
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * 将客户端信息存储到数据库
     */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource)
    {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource)
    {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 用来配置客户端详情服务
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception
    {
        clients.withClientDetails(clientDetailsService);

    }

    /**
     * 配置令牌服务(token services)
     */
    @Bean
    public AuthorizationServerTokenServices tokenService()
    {
        DefaultTokenServices service = new DefaultTokenServices();
        // 客户端详情服务
        service.setClientDetailsService(clientDetailsService);
        // 支持刷新令牌
        service.setSupportRefreshToken(true);
        // 令牌存储策略
        service.setTokenStore(tokenStore);

        // 令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(accessTokenConverter));
        service.setTokenEnhancer(tokenEnhancerChain);

        // 令牌默认有效期2小时
        service.setAccessTokenValiditySeconds(7200);
        // 刷新令牌默认有效期3天
        service.setRefreshTokenValiditySeconds(259200);
        return service;
    }

    /**
     * 用来配置令牌(token)的访问端点和令牌服务(token services)。
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
    {
        endpoints
                // 认证管理器，当你选择了资源所有者密码(password)授权类型的时候，请设置 这个属性注入一个 AuthenticationManager 对象。
                .authenticationManager(authenticationManager)
                // 这个属性是用来设置授权码服务的
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(tokenService())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
     * 配置令牌端点(Token Endpoint)的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
    {
        security
                // 这个endpoint当使用JwtToken且使用非对称加密时，资源服务用于获取公钥而开放的，这里指这个 endpoint完全公开。
                .tokenKeyAccess("permitAll()")
                // checkToken这个endpoint完全公开
                .checkTokenAccess("permitAll()")
                //  允许表单认证
                .allowFormAuthenticationForClients();
    }
}

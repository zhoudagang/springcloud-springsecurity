package com.everyman.springcloud.oauth2;

import com.everyman.springcloud.entity.Role;
import com.everyman.springcloud.entity.User;
import com.everyman.springcloud.service.IRoleService;
import com.everyman.springcloud.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhougang
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String uniqueId)
    {
        User user = userService.query(uniqueId).getData();
        log.info("load user by username :{}", user.toString());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                this.obtainGrantedAuthorities(user));
    }

    /**
     * 获得登录者所有角色的权限集合.
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities(User user)
    {
        List<Role> roles = roleService.query(user.getId()).getData();
        log.info("user:{},roles:{}", user.getUsername(), roles);

        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode())).collect(Collectors.toSet());
    }
}

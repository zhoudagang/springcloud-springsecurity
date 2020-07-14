package com.everyman.srpingcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everyman.srpingcloud.entity.User;
import com.everyman.srpingcloud.mapper.UserMapper;
import com.everyman.srpingcloud.service.IUserService;
import com.everyman.srpingcloud.service.IUsersRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhougang
 */
@Service
@Slf4j
public class UsersServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService
{
    @Resource
    private IUsersRoleService userRoleService;

    @Override
    public User getByUniqueId(String uniqueId)
    {
        User user = this.getOne(new QueryWrapper<User>()
                .eq("username", uniqueId)
                .or()
                .eq("mobile", uniqueId));
        user.setRoleIds(userRoleService.queryByUserId(user.getId()));
        return user;
    }
}

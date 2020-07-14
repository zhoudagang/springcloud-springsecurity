package com.everyman.srpingcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everyman.srpingcloud.entity.UserRole;
import com.everyman.srpingcloud.mapper.UserRoleMapper;
import com.everyman.srpingcloud.service.IUsersRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhougang
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUsersRoleService
{

    @Override
    public Set<String> queryByUserId(String userId)
    {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<UserRole> userRoleList = this.list(queryWrapper);
        return userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
    }
}

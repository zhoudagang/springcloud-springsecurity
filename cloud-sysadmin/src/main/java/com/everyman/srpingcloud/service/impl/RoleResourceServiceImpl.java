package com.everyman.srpingcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everyman.srpingcloud.entity.RoleResource;
import com.everyman.srpingcloud.mapper.RoleResourceMapper;
import com.everyman.srpingcloud.service.IRoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhougang
 */
@Service
@Slf4j
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService
{
    @Override
    public Set<String> queryByRoleId(String roleId)
    {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<RoleResource> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(RoleResource::getResourceId).collect(Collectors.toSet());
    }

    @Override
    public List<RoleResource> queryByRoleIds(Set<String> roleIds)
    {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        return this.list(queryWrapper);
    }
}

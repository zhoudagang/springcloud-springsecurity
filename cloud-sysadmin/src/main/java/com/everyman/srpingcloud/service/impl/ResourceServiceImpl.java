package com.everyman.srpingcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everyman.srpingcloud.entity.Resource;
import com.everyman.srpingcloud.entity.Role;
import com.everyman.srpingcloud.entity.RoleResource;
import com.everyman.srpingcloud.entity.User;
import com.everyman.srpingcloud.entity.po.BasePo;
import com.everyman.srpingcloud.mapper.ResourceMapper;
import com.everyman.srpingcloud.service.IResourceService;
import com.everyman.srpingcloud.service.IRoleResourceService;
import com.everyman.srpingcloud.service.IRoleService;
import com.everyman.srpingcloud.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 资源表
 *
 * @author zhougang
 */
@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService
{


    @javax.annotation.Resource
    private IRoleService roleService;

    @javax.annotation.Resource
    private IUserService userService;

    @javax.annotation.Resource
    private IRoleResourceService roleResourceService;


    @Override
    public List<Resource> query(String username)
    {
        //根据用户名查询到用户所拥有的角色
        User user = userService.getByUniqueId(username);
        List<Role> roles = roleService.query(user.getId());
        //提取用户所拥有角色id列表
        Set<String> roleIds = roles.stream().map(BasePo::getId).collect(Collectors.toSet());
        //根据角色列表查询到角色的资源的关联关系
        List<RoleResource> roleResources = roleResourceService.queryByRoleIds(roleIds);
        //根据资源列表查询出所有资源对象
        Set<String> resourceIds =
                roleResources.stream().map(RoleResource::getResourceId).collect(Collectors.toSet());
        //根据resourceId列表查询出resource对象
        return this.listByIds(resourceIds);
    }

    @Override
    public List<Resource> getAll()
    {
        return this.list();
    }
}

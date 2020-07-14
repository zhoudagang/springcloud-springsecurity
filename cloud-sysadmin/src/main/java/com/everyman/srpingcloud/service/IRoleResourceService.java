package com.everyman.srpingcloud.service;

import com.everyman.srpingcloud.entity.RoleResource;

import java.util.List;
import java.util.Set;

/**
 * @author zhougang
 */
public interface IRoleResourceService
{
    /**
     * 查询角色拥有资源id
     *
     * @param roleId 角色id
     * @return 角色拥有的资源id集合
     */
    Set<String> queryByRoleId(String roleId);

    /**
     * 根据角色id列表查询资源关系
     *
     * @param roleIds 角色id集合
     * @return 角色资源关系集合
     */
    List<RoleResource> queryByRoleIds(Set<String> roleIds);
}

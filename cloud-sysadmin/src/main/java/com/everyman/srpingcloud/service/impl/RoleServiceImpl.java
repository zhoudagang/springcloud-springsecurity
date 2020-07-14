package com.everyman.srpingcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.everyman.srpingcloud.entity.Role;
import com.everyman.srpingcloud.mapper.RoleMapper;
import com.everyman.srpingcloud.service.IRoleService;
import com.everyman.srpingcloud.service.IUsersRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author zhougang
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService
{

    @Resource
    private IUsersRoleService usersRoleService;

    @Override
    public List<Role> query(String userId)
    {
        Set<String> roleIds = usersRoleService.queryByUserId(userId);
        return this.listByIds(roleIds);
    }
}

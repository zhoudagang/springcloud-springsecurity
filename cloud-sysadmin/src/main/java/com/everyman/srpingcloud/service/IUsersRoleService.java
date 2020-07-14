package com.everyman.srpingcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.everyman.srpingcloud.entity.UserRole;

import java.util.Set;

/**
 * @author zhougang
 */
public interface IUsersRoleService extends IService<UserRole>
{

    /**
     * 根据userId查询用户拥有角色id集合
     *
     * @param userId 用户Id
     * @return Set<String>
     */
    Set<String> queryByUserId(String userId);
}

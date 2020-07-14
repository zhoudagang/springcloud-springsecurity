package com.everyman.srpingcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.everyman.srpingcloud.entity.Role;

import java.util.List;

/**
 * @author zhougang
 */
public interface IRoleService extends IService<Role>
{

    /**
     * 根据用户id查询用户拥有的角色
     *
     * @param userId 用户id
     * @return List<Role>
     */
    List<Role> query(String userId);

}

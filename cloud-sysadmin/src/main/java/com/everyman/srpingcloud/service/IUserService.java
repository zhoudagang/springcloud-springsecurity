package com.everyman.srpingcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.everyman.srpingcloud.entity.User;

/**
 * @author zhougang
 */
public interface IUserService extends IService<User>
{
    /**
     * 根据用户唯一标识获取用户信息
     * 目前用户标识 用户名或mobile
     *
     * @param uniqueId 唯一标识
     * @return Users
     */
    User getByUniqueId(String uniqueId);

}

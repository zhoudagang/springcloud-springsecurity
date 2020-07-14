package com.everyman.srpingcloud.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.everyman.srpingcloud.entity.Resource;

import java.util.List;

/**
 * 资源表
 *
 * @author zhougang
 */
public interface IResourceService extends IService<Resource>
{

    /**
     * 根据username查询角色拥有的资源
     *
     * @param username 用户名
     * @return List<Resource>
     */
    List<Resource> query(String username);


    /**
     * 查询所有资源
     *
     * @return List<Resource>
     */
    List<Resource> getAll();
}

package com.everyman.springcloud.entity;

import com.everyman.srpingcloud.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhougang
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Role extends BasePo
{
    /**
     * 角色code
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 简介
     */
    private String description;
}

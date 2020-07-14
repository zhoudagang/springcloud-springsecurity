package com.everyman.srpingcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.everyman.srpingcloud.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源表
 *
 * @author zhougang
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("resource")
public class Resource extends BasePo
{

    private static final long serialVersionUID = 1L;

    /**
     * 资源code
     */
    private String code;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源url
     */
    private String url;

    /**
     * 资源方法
     */
    private String method;

    /**
     * 简介
     */
    private String description;


}

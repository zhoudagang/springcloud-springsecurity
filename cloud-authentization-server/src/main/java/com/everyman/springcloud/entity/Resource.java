package com.everyman.springcloud.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 资源表
 *
 * @author zhougang
 */
@Data
@NoArgsConstructor
public class Resource implements Serializable
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

    private String id;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;
}
package com.everyman.srpingcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.everyman.srpingcloud.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhougang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_resource_relation")
public class RoleResource extends BasePo
{
    private String roleId;
    private String resourceId;
}


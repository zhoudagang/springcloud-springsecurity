package com.everyman.srpingcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.everyman.srpingcloud.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和角色关系表
 *
 * @author zhougang
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_role_relation")
public class UserRole extends BasePo
{

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;


}

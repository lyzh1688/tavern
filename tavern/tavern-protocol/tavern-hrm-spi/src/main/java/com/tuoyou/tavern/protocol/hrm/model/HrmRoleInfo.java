package com.tuoyou.tavern.protocol.hrm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("HRM_ROLE_INFO")
public class HrmRoleInfo extends Model<HrmRoleInfo> {
    @NotNull(message = "roleId 不能为空")
    @TableId
    private String roleId;

    @NotNull(message = "roleName 不能为空")
    private String roleName;

    private String parentRole;

}
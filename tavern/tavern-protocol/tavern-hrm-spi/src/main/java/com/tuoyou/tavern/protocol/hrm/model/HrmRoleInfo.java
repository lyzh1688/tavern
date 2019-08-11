package com.tuoyou.tavern.protocol.hrm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("HRM_ROLE_INFO")
public class HrmRoleInfo extends Model<HrmRoleInfo> {
    @TableId
    private String roleId;

    private String roleName;

    private String parentRole;

}
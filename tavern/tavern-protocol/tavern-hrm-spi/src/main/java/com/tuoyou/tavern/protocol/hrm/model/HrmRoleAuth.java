package com.tuoyou.tavern.protocol.hrm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("HRM_ROLE_AUTH")
public class HrmRoleAuth extends Model<HrmRoleAuth> {
    private String roleId;

    private String item;

    private String authType;

    private String itemType;

}
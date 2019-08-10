package com.tuoyou.tavern.protocol.hrm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("HRM_USER_ROLE_REL")
public class HrmUserRoleRel extends Model<HrmUserRoleRel> {
    @TableId
    private String userId;

    @TableId
    private String roleId;

    private LocalDateTime updateDate;

}
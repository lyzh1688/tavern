package com.tuoyou.tavern.protocol.authcenter.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("AUTH_MENU_ROLE_REL")
public class AuthMenuRoleRel extends Model<AuthMenuRoleRel> {
    @TableId
    private String menuId;
    @TableId
    private String roleId;

    private LocalDateTime updateDate;

}
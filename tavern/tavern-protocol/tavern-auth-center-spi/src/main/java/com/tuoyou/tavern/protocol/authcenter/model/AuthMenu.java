package com.tuoyou.tavern.protocol.authcenter.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("AUTH_MENU")
public class AuthMenu extends Model<AuthMenu> {
    @NotNull(message = "menuId不能为空")
    @TableId
    private String menuId;

    private String menuUrl;

    private String menuName;

    private String parentId;

    private String perms;

    private Integer type;

    private String icon;

    private Integer orderNum;

    private LocalDateTime updateDate;

    // 非数据库字段
    private String parentName;
    // 非数据库字段
    private Integer level;
    // 非数据库字段
    private List<AuthMenu> children;
}
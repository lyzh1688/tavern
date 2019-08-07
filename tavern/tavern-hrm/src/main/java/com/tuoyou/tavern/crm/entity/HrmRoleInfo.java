package com.tuoyou.tavern.hrm.entity;

public class HrmRoleInfo {
    private String roleId;

    private String roleName;

    private String parentRole;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getParentRole() {
        return parentRole;
    }

    public void setParentRole(String parentRole) {
        this.parentRole = parentRole == null ? null : parentRole.trim();
    }
}
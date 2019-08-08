package com.tuoyou.tavern.auth.libs;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
public class AuthTokenFactor {

    public static class Factor{
        public static final String USER_ACCNT = "USER_ACCNT";
        public static final String USER_TYPE = "USER_TYPE";
        public static final String ROLE_ID = "ROLE_ID";
    }

    private String userAccnt;
    private String userType;
    private String roleId;

    public String getUserAccnt() {
        return userAccnt;
    }

    public void setUserAccnt(String userAccnt) {
        this.userAccnt = userAccnt;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

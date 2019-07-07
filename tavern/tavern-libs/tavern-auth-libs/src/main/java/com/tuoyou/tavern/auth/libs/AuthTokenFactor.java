package com.tuoyou.tavern.auth.libs;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
public class AuthTokenFactor {

    public static class Factor{
        public static final String USER_ACCNT = "USER_ACCNT";
        public static final String USER_TYPE = "USER_TYPE";
    }

    private String userAccnt;
    private String userType;

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
}

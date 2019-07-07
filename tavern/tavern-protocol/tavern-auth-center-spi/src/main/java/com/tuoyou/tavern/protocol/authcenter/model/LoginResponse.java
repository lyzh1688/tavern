package com.tuoyou.tavern.protocol.authcenter.model;

import com.tuoyou.tavern.protocol.common.TavernResponse;

/**
 * Created by 刘悦之 on 2019/7/7.
 */
public class LoginResponse extends TavernResponse {

    String userAccnt;
    String userType;
    boolean loginSuccess;

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

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }
}

package com.tuoyou.tavern.protocol.authcenter.model;

/**
 * Created by 刘悦之 on 2019/7/7.
 */
public class Login {

    String userAccnt;
    String password;
    String userType;

    public String getUserAccnt() {
        return userAccnt;
    }

    public void setUserAccnt(String userAccnt) {
        this.userAccnt = userAccnt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

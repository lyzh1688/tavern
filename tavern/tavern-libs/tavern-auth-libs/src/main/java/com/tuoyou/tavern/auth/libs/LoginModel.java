package com.tuoyou.tavern.auth.libs;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
public class LoginModel {
    private String username;
    private String password;
    private JSONObject userDetail = new JSONObject();

    public LoginModel(String username, String password,String userType) {
        this.username = username;
        this.password = password;
        this.userDetail.put(LoginField.userType,userType);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSONObject getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(JSONObject userDetail) {
        this.userDetail = userDetail;
    }

    public static class LoginField {
        public static String userName = "userName";
        public static String password = "password";
        public static String userType = "userType";

    }
}

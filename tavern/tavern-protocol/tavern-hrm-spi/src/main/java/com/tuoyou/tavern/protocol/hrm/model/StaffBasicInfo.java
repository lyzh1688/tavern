package com.tuoyou.tavern.protocol.hrm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘悦之 on 2019/7/5.
 */
public class StaffBasicInfo {
    String userId;
    String userAccnt;
    String userName;
    List<String> roleList = new ArrayList<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccnt() {
        return userAccnt;
    }

    public void setUserAccnt(String userAccnt) {
        this.userAccnt = userAccnt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }
}
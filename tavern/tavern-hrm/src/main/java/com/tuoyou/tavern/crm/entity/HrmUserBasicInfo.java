package com.tuoyou.tavern.hrm.entity;

import java.util.Date;

public class HrmUserBasicInfo {
    private String userId;

    private String userName;

    private String userAccnt;

    private String password;

    private String isValid;

    private Date updateDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserAccnt() {
        return userAccnt;
    }

    public void setUserAccnt(String userAccnt) {
        this.userAccnt = userAccnt == null ? null : userAccnt.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
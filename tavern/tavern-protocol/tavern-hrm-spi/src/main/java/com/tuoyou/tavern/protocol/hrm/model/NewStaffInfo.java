package com.tuoyou.tavern.protocol.hrm.model;

/**
 * Created by 刘悦之 on 2019/7/5.
 */
public class NewStaffInfo extends StaffBasicInfo {
    String defaultPassword;;

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }
}

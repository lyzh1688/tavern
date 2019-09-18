package com.tuoyou.tavern.protocol.hrm.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘悦之 on 2019/7/5.
 */
@Data
public class StaffBasicInfo {
    private String userId;
    private String userAccnt;
    private String userName;
    private String password;
    private String salt;
    private String roleNames;
    private String updateDate;
    private List<HrmUserRoleRelVO> userRoles = new ArrayList<>();

}

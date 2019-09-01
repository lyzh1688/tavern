package com.tuoyou.tavern.protocol.authcenter.model;

import lombok.Data;

/**
 * Created by 刘悦之 on 2019/7/7.
 */
@Data
public class LoginVO {

    private String userId;
    private String userAccnt;
    private String password;
    private String userType;
    private String token;
    private String roles;
    private boolean loginSuccess;


}

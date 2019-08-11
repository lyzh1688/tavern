package com.tuoyou.tavern.protocol.authcenter.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by 刘悦之 on 2019/7/7.
 */
@Data
public class LoginDTO {

    @NotNull(message = "账号不能为空")
    String userAccnt;
    @NotNull(message = "密码不能为空")
    String password;
    @NotNull(message = "账户类型不能为空")
    String userType;
    String token;

}

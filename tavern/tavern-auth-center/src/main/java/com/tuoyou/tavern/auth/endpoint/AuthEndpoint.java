package com.tuoyou.tavern.auth.endpoint;

import com.tuoyou.tavern.auth.libs.UserTypeDict;
import com.tuoyou.tavern.auth.model.Login;
import com.tuoyou.tavern.auth.model.LoginResponse;
import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘悦之 on 2019/7/7.
 */
@RestController
public class AuthEndpoint {
    @RequestMapping(name = "/login",method = RequestMethod.POST)
    public LoginResponse login(@RequestBody Login login){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setRetCode(RetCode.SUCCESS);
        loginResponse.setLoginSuccess(true);
        loginResponse.setUserAccnt("liuyuezhi");
        loginResponse.setUserType(UserTypeDict.staff);
        loginResponse.setRetCode(0);
        return loginResponse;
    }
}
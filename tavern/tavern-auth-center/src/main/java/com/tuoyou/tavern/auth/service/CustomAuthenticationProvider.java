package com.tuoyou.tavern.auth.service;

import com.alibaba.fastjson.JSONObject;
import com.tuoyou.tavern.auth.libs.LoginModel;
import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.model.StaffInfo;
import com.tuoyou.tavern.protocol.hrm.spi.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by 刘悦之 on 2019/6/27.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    StaffService staffService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        JSONObject userDetail = (JSONObject) authentication.getDetails();
        String userType = userDetail.getString(LoginModel.LoginField.userType);
        try {
            TavernResponse response = staffService.queryStaffBasicInfo(name, password);
            if (response.getRetCode() == RetCode.SUCCESS) {
                System.out.println(true);
            }
            else {
                System.out.println(false);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new UsernamePasswordAuthenticationToken(name,password);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}

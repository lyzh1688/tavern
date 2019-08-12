package com.tuoyou.tavern.auth.service.impl;

import com.tuoyou.tavern.auth.service.LoginService;
import com.tuoyou.tavern.protocol.authcenter.dto.LoginDTO;
import com.tuoyou.tavern.protocol.authcenter.model.LoginVO;
import com.tuoyou.tavern.protocol.authcenter.reponse.LoginResponse;
import com.tuoyou.tavern.protocol.crm.spi.CustomService;
import com.tuoyou.tavern.protocol.hrm.spi.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private StaffService staffService;
    @Autowired
    private CustomService customService;


    @Override
    public LoginResponse login(LoginDTO login) {
        LoginResponse loginResponse = new LoginResponse();
//        if (login.getUserType().equals("1")) {
//            StaffInfoResponse staffInfoResponse = this.staffService.queryStaffBasicInfo(login.getUserAccnt(), login.getPassword());
//            if (staffInfoResponse.getRetCode() == 0) {
//                LoginVO loginVO = new LoginVO();
//                loginVO.setUserAccnt(staffInfoResponse.getData().getUserAccnt());
//                loginVO.setPassword(staffInfoResponse.getData().getPassword());
//                loginVO.setUserType("1");
//                List<String> roleList = staffInfoResponse.getData().getRoleList().stream().map(info -> info.getRoleId()).collect(Collectors.toList());
//                loginVO.setRoles(StringUtils.join(roleList, ","));
//                loginVO.setLoginSuccess(true);
//                loginResponse.setData(loginVO);
//            } else {
//                loginResponse.setRetCode(staffInfoResponse.getRetCode());
//                loginResponse.setRetMessage(staffInfoResponse.getRetMessage());
//            }
//            return loginResponse;
//        } else {
//            CustomInfoResponse customInfoResponse = this.customService.getCustomInfo(login.getUserAccnt(), login.getPassword());
//            if (customInfoResponse.getRetCode() == 0) {
//                LoginVO loginVO = new LoginVO();
//                loginVO.setUserAccnt(customInfoResponse.getData().getCustomAccnt());
//                loginVO.setPassword(customInfoResponse.getData().getPassword());
//                loginVO.setUserType("1");
//                loginVO.setRoles(customInfoResponse.getData().getRoleId());
//                loginVO.setLoginSuccess(true);
//                loginResponse.setData(loginVO);
//            } else {
//                loginResponse.setRetCode(customInfoResponse.getRetCode());
//                loginResponse.setRetMessage(customInfoResponse.getRetMessage());
//            }


        //mock
        LoginVO loginVO = new LoginVO();
        loginVO.setUserAccnt("admin");
        loginVO.setPassword("admin");
        loginVO.setUserType("1");
        loginVO.setLoginSuccess(true);
        loginResponse.setData(loginVO);


        return loginResponse;
    }
}

package com.tuoyou.tavern.hrm.endpoint;

import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.hrm.model.NewStaffInfo;
import com.tuoyou.tavern.protocol.hrm.response.NewStaffResponse;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;
import com.tuoyou.tavern.protocol.hrm.response.StaffInfoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@RestController
public class StaffEndpoint {

    /*
    * 创建用户
    * */
    @RequestMapping(value = "/hrm/staff", method = RequestMethod.POST)
    public NewStaffResponse createStaff(@RequestBody StaffBasicInfo staffBasicInfo){
        NewStaffInfo newStaffInfo = new NewStaffInfo();
        BeanUtils.copyProperties(staffBasicInfo,newStaffInfo);
        newStaffInfo.setDefaultPassword(UUID.randomUUID().toString());
        NewStaffResponse newStaffResponse = new NewStaffResponse();
        newStaffResponse.setRetCode(RetCode.SUCCESS);
        newStaffResponse.setStaffInfo(newStaffInfo);
        return newStaffResponse;
    }

    /*
    * 查询用户基本信息
    * */
    //RESOURCE-PATH:/hrm/staff?accnt=${accnt}&password=${password}
    @RequestMapping(value = "/hrm/staff", method = RequestMethod.GET, params = {"accnt", "password"})
    public StaffInfoResponse queryStaffBasicInfo(String accnt, String password) {
        System.out.println(accnt + ":" + password);
        if (accnt.equals("liuyuezhi") && password.equals("111222")) {
            StaffBasicInfo staffBasicInfo = new StaffBasicInfo();
            staffBasicInfo.setUserId("123456");
            staffBasicInfo.setUserAccnt("liuyuezhi");
            staffBasicInfo.setUserName("刘悦之");
            staffBasicInfo.getRoleList().add("ADMIN");
            StaffInfoResponse staffInfoResponse = new StaffInfoResponse();
            staffInfoResponse.setStaffBasicInfo(staffBasicInfo);
            staffInfoResponse.setRetCode(RetCode.SUCCESS);
            return staffInfoResponse;
        } else {
            StaffInfoResponse staffInfoResponse = new StaffInfoResponse();
            staffInfoResponse.setRetCode(RetCode.AUTH_FAILED);
            staffInfoResponse.setRetMessage("用户不存在");
            return staffInfoResponse;
        }
    }
}

package com.tuoyou.tavern.crm.endpoint;

import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.hrm.model.StaffInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@RestController
public class StaffEndpoint {

    /*
    * 查询用户基本信息
    * */
    //RESOURCE-PATH:/hrm/staff?accnt=${accnt}&password=${password}
    @RequestMapping(value = "/hrm/staff",method = RequestMethod.GET,params = {"accnt","password"})
    public StaffInfo queryStaffBasicInfo(String accnt,String password){
        if(accnt.equals("liuyuezhi") && password.equals("111222")){
            StaffInfo staffInfo = new StaffInfo();
            staffInfo.setUserId("123456");
            staffInfo.setUserAccnt("liuyuezhi");
            staffInfo.setUserName("刘悦之");
            staffInfo.getRoleList().add("ADMIN");
            staffInfo.setRetCode(RetCode.SUCCESS);
            return staffInfo;
        }
        else {
            StaffInfo staffInfo = new StaffInfo();
            staffInfo.setRetCode(RetCode.AUTH_FAILED);
            staffInfo.setRetMessage("用户不存在");
            return staffInfo;
        }
    }
}

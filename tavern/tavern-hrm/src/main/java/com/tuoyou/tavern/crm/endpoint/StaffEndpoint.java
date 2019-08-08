package com.tuoyou.tavern.crm.endpoint;

import com.tuoyou.tavern.crm.service.HrmUserBasicInfoService;
import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.hrm.model.NewStaffInfo;
import com.tuoyou.tavern.protocol.hrm.response.NewStaffResponse;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;
import com.tuoyou.tavern.protocol.hrm.response.StaffInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@RestController
@AllArgsConstructor
public class StaffEndpoint {
    private HrmUserBasicInfoService hrmUserBasicInfoService;


    /*
     * 创建用户
     * */
    @RequestMapping(value = "/hrm/staff", method = RequestMethod.POST)
    public NewStaffResponse createStaff(@RequestBody StaffBasicInfo staffBasicInfo) {
        NewStaffInfo newStaffInfo = new NewStaffInfo();
        BeanUtils.copyProperties(staffBasicInfo, newStaffInfo);
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
        StaffBasicInfo staffBasicInfo = this.hrmUserBasicInfoService.queryStaffBasicInfo(accnt, password);
        StaffInfoResponse staffInfoResponse = new StaffInfoResponse();
        if (Objects.isNull(staffBasicInfo)) {
            staffInfoResponse.setRetCode(RetCode.AUTH_FAILED);
            staffInfoResponse.setRetMessage("用户不存在");
            return staffInfoResponse;
        } else {
            staffInfoResponse.setData(staffBasicInfo);
            return staffInfoResponse;
        }
    }
}

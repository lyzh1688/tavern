package com.tuoyou.tavern.protocol.hrm.spi;

import com.tuoyou.tavern.protocol.common.annotation.PathParam;
import com.tuoyou.tavern.protocol.hrm.response.NewStaffResponse;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;
import com.tuoyou.tavern.protocol.hrm.response.StaffInfoResponse;

/**
 * Created by 刘悦之 on 2019/7/1.
 */
public interface StaffService {

    NewStaffResponse createStaff(StaffBasicInfo staffBasicInfo);

    StaffInfoResponse queryStaffBasicInfo(@PathParam(name = "accnt") String accnt, @PathParam(name = "password") String password);
}

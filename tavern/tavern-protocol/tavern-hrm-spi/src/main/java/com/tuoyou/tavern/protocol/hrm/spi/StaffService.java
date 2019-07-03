package com.tuoyou.tavern.protocol.hrm.spi;

import com.tuoyou.tavern.protocol.common.annotation.PathParam;
import com.tuoyou.tavern.protocol.hrm.model.StaffInfo;

/**
 * Created by 刘悦之 on 2019/7/1.
 */
public interface StaffService {
    public StaffInfo queryStaffBasicInfo(@PathParam(name = "accnt") String accnt, @PathParam(name = "password") String password);
}

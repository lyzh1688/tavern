package com.tuoyou.tavern.protocol.hrm.response;

import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
public class StaffInfoResponse extends TavernResponse {
    StaffBasicInfo staffBasicInfo;

    public StaffBasicInfo getStaffBasicInfo() {
        return staffBasicInfo;
    }

    public void setStaffBasicInfo(StaffBasicInfo staffBasicInfo) {
        this.staffBasicInfo = staffBasicInfo;
    }
}

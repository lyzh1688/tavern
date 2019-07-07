package com.tuoyou.tavern.protocol.hrm.response;

import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.model.NewStaffInfo;

/**
 * Created by 刘悦之 on 2019/7/5.
 */
public class NewStaffResponse extends TavernResponse{

    NewStaffInfo staffInfo;

    public NewStaffInfo getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(NewStaffInfo staffInfo) {
        this.staffInfo = staffInfo;
    }
}

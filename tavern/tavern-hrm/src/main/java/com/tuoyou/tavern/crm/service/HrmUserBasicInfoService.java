package com.tuoyou.tavern.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserBasicInfo;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/08 <br>
 */
public interface HrmUserBasicInfoService extends IService<HrmUserBasicInfo> {

    StaffBasicInfo queryStaffBasicInfo(String accnt, String password);

}

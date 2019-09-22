package com.tuoyou.tavern.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.hrm.dto.StaffInfoDTO;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserBasicInfo;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/08 <br>
 */
public interface HrmUserBasicInfoService extends IService<HrmUserBasicInfo> {

    StaffBasicInfo queryStaffBasicInfo(String accnt, String password);

    void saveStaffBasicInfo(StaffBasicInfo staffBasicInfo);

    IPage<StaffBasicInfo> page(Page page, StaffInfoDTO staffInfoDTO);

    List<HrmUserBasicInfo> getStaffByRole(String roleId);



}

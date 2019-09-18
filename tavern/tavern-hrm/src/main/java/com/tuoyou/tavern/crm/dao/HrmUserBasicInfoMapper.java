package com.tuoyou.tavern.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.hrm.dto.StaffInfoDTO;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserBasicInfo;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrmUserBasicInfoMapper extends BaseMapper<HrmUserBasicInfo> {

    StaffBasicInfo selectStaffBasicInfo(@Param("accnt") String accnt);

    IPage<StaffBasicInfo> selectStaffPage(Page page, @Param("query") StaffInfoDTO staffInfoDTO);

    List<HrmUserBasicInfo> selectStaffByRole(@Param("roleId") String roleId);
}
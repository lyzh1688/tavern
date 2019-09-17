package com.tuoyou.tavern.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.hrm.dto.StaffRoleDTO;
import com.tuoyou.tavern.protocol.hrm.model.HrmRoleInfo;
import org.apache.ibatis.annotations.Param;

public interface HrmRoleInfoMapper extends BaseMapper<HrmRoleInfo> {

    IPage<HrmRoleInfo> selectStaffRolePage(Page page, @Param("query") StaffRoleDTO staffRoleDTO);
}
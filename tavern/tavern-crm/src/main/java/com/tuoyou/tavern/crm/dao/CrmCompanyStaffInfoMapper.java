package com.tuoyou.tavern.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.crm.dto.CustomStaffQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyInfoVO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyStaffInfo;
import org.apache.ibatis.annotations.Param;

public interface CrmCompanyStaffInfoMapper extends BaseMapper<CrmCompanyStaffInfo> {

    IPage<CrmCompanyInfoVO> selectCrmStaffPage(Page page, @Param("query") CustomStaffQueryDTO customCompanyQueryDTO);
}
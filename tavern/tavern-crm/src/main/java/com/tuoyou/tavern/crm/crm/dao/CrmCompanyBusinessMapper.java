package com.tuoyou.tavern.crm.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyBusinessQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBusiness;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBusinessVO;
import org.apache.ibatis.annotations.Param;

public interface CrmCompanyBusinessMapper extends BaseMapper<CrmCompanyBusiness> {

    IPage<CrmCompanyBusinessVO> selectCompanyBusinessPage(Page page, @Param("query") CustomCompanyBusinessQueryDTO queryDTO);
}
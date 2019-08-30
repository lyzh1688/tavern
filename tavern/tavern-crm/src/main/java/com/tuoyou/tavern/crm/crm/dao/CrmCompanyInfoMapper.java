package com.tuoyou.tavern.crm.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrmCompanyInfoMapper extends BaseMapper<CrmCompanyInfo> {

    IPage<CrmCompanyInfo> selectCompanyInfoPage(Page page, @Param("query") CustomCompanyQueryDTO customCompanyQueryDTO);

    List<CrmCompanyInfo> selectCompanyByCustomId(@Param("customId") String customId);

}
package com.tuoyou.tavern.crm.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.crm.dto.CustomThirdPartyQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmThirdPartyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrmThirdPartyInfoMapper extends BaseMapper<CrmThirdPartyInfo> {

    IPage<CrmThirdPartyInfo> selectPage(Page page, @Param("query") CustomThirdPartyQueryDTO customCompanyOrderQueryDTO);
}
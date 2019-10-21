package com.tuoyou.tavern.crm.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyOrderQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CrmCustomOrderInfoMapper extends BaseMapper<CrmCustomOrderInfo> {

    IPage<CrmCustomOrderInfo> selectCrmOrderPage(Page page, @Param("query") CustomCompanyOrderQueryDTO customCompanyOrderQueryDTO);

    void insertOne(CrmCustomOrderInfo crmCustomOrderInfo);

    void updateOne(CrmCustomOrderInfo crmCustomOrderInfo);
}
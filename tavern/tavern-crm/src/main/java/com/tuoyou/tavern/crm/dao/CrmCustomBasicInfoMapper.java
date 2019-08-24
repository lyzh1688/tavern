package com.tuoyou.tavern.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.crm.dto.CustomQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomBasicInfo;
import org.apache.ibatis.annotations.Param;

public interface CrmCustomBasicInfoMapper extends BaseMapper<CrmCustomBasicInfo> {
    IPage<CrmCustomBasicInfo> selectBasicInfoPage(Page page, @Param("query") CustomQueryDTO customQueryDTO);
}
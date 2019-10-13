package com.tuoyou.tavern.crm.crm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.crm.dto.CustomOrderBizQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyOrderDetailVO;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderBusinessRel;
import org.apache.ibatis.annotations.Param;

public interface CrmOrderBusinessRelMapper extends BaseMapper<CrmOrderBusinessRel> {
    IPage selectCrmOrderBusinessPage(Page page, @Param("query") CustomOrderBizQueryDTO customCompanyOrderQueryDTO);

    CrmCompanyOrderDetailVO selectCrmCompanyOrderDetail(@Param("eventId") String eventId,
                                                        @Param("companyId") String companyId );
}

package com.tuoyou.tavern.crm.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.dto.CrmOrderBusinessRelDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomOrderBizQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmBusinessDict;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyOrderDetailVO;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderBusinessRel;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface CrmCustomOrderBusinessRelService extends IService<CrmOrderBusinessRel> {

    void saveOrderBusinessAndStartWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO);

    void saveOrderBusiness(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId);

    void rollBackOrderBusiness(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId);

    IPage getCrmOrderBusinessPage(Page page, CustomOrderBizQueryDTO customCompanyOrderQueryDTO);

    CrmCompanyOrderDetailVO getCrmCompanyOrderDetail(String eventId,String companyId);

}

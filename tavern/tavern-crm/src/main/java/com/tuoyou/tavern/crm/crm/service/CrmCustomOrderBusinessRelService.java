package com.tuoyou.tavern.crm.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.dto.CrmOrderBusinessRelDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmBusinessDict;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderBusinessRel;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface CrmCustomOrderBusinessRelService extends IService<CrmOrderBusinessRel> {

    void saveOrderBusinessAndStartWorkFlow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO);

    void saveOrderBusiness(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO, String eventId);

    void startWorkFLow(CrmOrderBusinessRelDTO crmOrderBusinessRelDTO);

}

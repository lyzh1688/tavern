package com.tuoyou.tavern.crm.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.model.CrmBusinessDict;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBankRel;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface CrmCustomBusinessDictService extends IService<CrmBusinessDict> {
    List<CrmBusinessDict> getCrmOrderBusinessByOrderId(String orderId);
}

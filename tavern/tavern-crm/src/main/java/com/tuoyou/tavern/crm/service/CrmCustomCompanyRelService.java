package com.tuoyou.tavern.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyRelDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmBankInfo;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomCompanyRel;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface CrmCustomCompanyRelService extends IService<CrmCustomCompanyRel> {
    void deleteCrmCompanyRelInfo(List<CrmCompanyRelDTO> crmCompanyRelDTOList);

}

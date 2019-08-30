package com.tuoyou.tavern.crm.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.model.CrmBankInfo;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface CrmCustomBankInfoService extends IService<CrmBankInfo> {

    List<CrmBankInfo> getCrmBankInfoByCompanyId(String companyId);

}

package com.tuoyou.tavern.crm.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.dto.CrmThirdPartyInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomThirdPartyQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmBusinessDict;
import com.tuoyou.tavern.protocol.crm.model.CrmThirdPartyInfo;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface CrmCustomThirdPartyInfoService extends IService<CrmThirdPartyInfo> {

    void createCrmCustomThirdPartyInfo(CrmThirdPartyInfoDTO crmThirdPartyInfoDTO);

    IPage<CrmThirdPartyInfo> queryCustomThirdPartyInfoPage(Page page, CustomThirdPartyQueryDTO customCompanyOrderQueryDTO);
}

package com.tuoyou.tavern.crm.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyInfo;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyInfoVO;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
public interface CrmCustomCompanyInfoService extends IService<CrmCompanyInfo> {

    void createCrmCompanyInfo(CrmCompanyInfoDTO crmCompanyInfoDTO);

    IPage<CrmCompanyInfoVO> getCompanyInfoPage(Page page, CustomCompanyQueryDTO customCompanyQueryDTO);

    List<CrmCompanyInfo> getCompanyByCustomId(String customId);

}

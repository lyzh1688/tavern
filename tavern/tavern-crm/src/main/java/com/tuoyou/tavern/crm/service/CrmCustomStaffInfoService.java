package com.tuoyou.tavern.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyStaffInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomStaffQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyInfoVO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyStaffInfo;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
public interface CrmCustomStaffInfoService extends IService<CrmCompanyStaffInfo> {

    void createCrmCompanyStaffInfo(CrmCompanyStaffInfoDTO crmCompanyStaffInfoDTO);

    IPage<CrmCompanyInfoVO> getCrmStaffPage(Page page, CustomStaffQueryDTO customCompanyQueryDTO);

}

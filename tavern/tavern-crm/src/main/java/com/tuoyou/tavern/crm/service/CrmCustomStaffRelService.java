package com.tuoyou.tavern.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyStaffInfoDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyStaffInfo;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyStaffRel;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
public interface CrmCustomStaffRelService extends IService<CrmCompanyStaffRel> {

    void deleteCrmCustomStaffRel(List<CrmCompanyStaffRel> crmCompanyStaffRelList);

}

package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.crm.CrmCompanyStaffRelMapper;
import com.tuoyou.tavern.crm.service.CrmCustomStaffRelService;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyStaffRel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomStaffRelServiceImpl extends ServiceImpl<CrmCompanyStaffRelMapper, CrmCompanyStaffRel> implements CrmCustomStaffRelService {

    @Override
    public void deleteCrmCustomStaffRel(List<CrmCompanyStaffRel> crmCompanyStaffRelList) {
        crmCompanyStaffRelList.forEach(rel -> this.remove(Wrappers.<CrmCompanyStaffRel>query(rel)));
    }
}

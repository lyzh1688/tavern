package com.tuoyou.tavern.crm.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.crm.dao.CrmOrderBusinessRelMapper;
import com.tuoyou.tavern.crm.crm.service.CrmCustomOrderRelService;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderBusinessRel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */

@AllArgsConstructor
@Service
public class CrmCustomOrderRelServiceImpl extends ServiceImpl<CrmOrderBusinessRelMapper, CrmOrderBusinessRel> implements CrmCustomOrderRelService {

   /* @Transactional
    @Override
    public void deleteCrmCompanyRelInfo(List<CrmCompanyRelDTO> crmCompanyRelDTOList) {
        if (!crmCompanyRelDTOList.isEmpty()) {
            List<CrmCustomCompanyRel> crmCustomCompanyRelList = crmCompanyRelDTOList
                    .stream()
                    .map(rel -> {
                        CrmCustomCompanyRel crmCustomCompanyRel = new CrmCustomCompanyRel();
                        BeanUtils.copyProperties(rel, crmCustomCompanyRel);
                        return crmCustomCompanyRel;
                    }).collect(Collectors.toList());
            crmCustomCompanyRelList.forEach(rel -> this.remove(Wrappers.<CrmCustomCompanyRel>query().lambda()
                    .eq(CrmCustomCompanyRel::getCompanyId, rel.getCompanyId())
                    .eq(CrmCustomCompanyRel::getCustomId, rel.getCustomId())));
        }
    }*/

}

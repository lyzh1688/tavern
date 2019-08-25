package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.CrmCustomCompanyRelMapper;
import com.tuoyou.tavern.crm.service.CrmCustomCompanyRelService;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyRelDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomCompanyRel;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */

@AllArgsConstructor
@Service
public class CrmCustomCompanyRelServiceImpl extends ServiceImpl<CrmCustomCompanyRelMapper, CrmCustomCompanyRel> implements CrmCustomCompanyRelService {

    @Transactional
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
    }

}

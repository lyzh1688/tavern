package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.CrmBusinessDictMapper;
import com.tuoyou.tavern.crm.dao.CrmCompanyBankRelMapper;
import com.tuoyou.tavern.crm.service.CrmCompanyBankRelService;
import com.tuoyou.tavern.crm.service.CrmCustomBusinessDictService;
import com.tuoyou.tavern.protocol.crm.model.CrmBusinessDict;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBankRel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomBusinessDictServiceImpl extends ServiceImpl<CrmBusinessDictMapper, CrmBusinessDict> implements CrmCustomBusinessDictService {
}

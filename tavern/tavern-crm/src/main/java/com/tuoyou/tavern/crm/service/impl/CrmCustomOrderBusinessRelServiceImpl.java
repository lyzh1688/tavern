package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.CrmCompanyBankRelMapper;
import com.tuoyou.tavern.crm.dao.CrmOrderBusinessRelMapper;
import com.tuoyou.tavern.crm.service.CrmCompanyBankRelService;
import com.tuoyou.tavern.crm.service.CrmCustomOrderBusinessRelService;
import com.tuoyou.tavern.protocol.crm.model.CrmBusinessDict;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBankRel;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderBusinessRel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomOrderBusinessRelServiceImpl extends ServiceImpl<CrmOrderBusinessRelMapper, CrmOrderBusinessRel> implements CrmCustomOrderBusinessRelService {
}

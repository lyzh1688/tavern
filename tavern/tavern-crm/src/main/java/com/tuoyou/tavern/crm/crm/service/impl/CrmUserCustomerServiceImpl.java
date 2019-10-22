package com.tuoyou.tavern.crm.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.crm.dao.CrmCompanyBankRelMapper;
import com.tuoyou.tavern.crm.crm.dao.CrmUserCustomerInfoMapper;
import com.tuoyou.tavern.crm.crm.service.CrmCompanyBankRelService;
import com.tuoyou.tavern.crm.crm.service.CrmUserCustomerService;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBankRel;
import com.tuoyou.tavern.protocol.crm.model.CrmUserCustomerInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class CrmUserCustomerServiceImpl extends ServiceImpl<CrmUserCustomerInfoMapper, CrmUserCustomerInfo> implements CrmUserCustomerService {
}

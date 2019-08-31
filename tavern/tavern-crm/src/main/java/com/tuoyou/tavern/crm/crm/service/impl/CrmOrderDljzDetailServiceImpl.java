package com.tuoyou.tavern.crm.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dfzq.obgear.framework.spring.db.aspect.anno.TargetDataSource;
import com.tuoyou.tavern.crm.crm.dao.CrmCompanyBankRelMapper;
import com.tuoyou.tavern.crm.crm.dao.CrmOrderDljzDetailMapper;
import com.tuoyou.tavern.crm.crm.service.CrmCompanyBankRelService;
import com.tuoyou.tavern.crm.crm.service.CrmOrderDljzDetailService;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBankRel;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderDljzDetail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class CrmOrderDljzDetailServiceImpl extends ServiceImpl<CrmOrderDljzDetailMapper, CrmOrderDljzDetail> implements CrmOrderDljzDetailService {
}

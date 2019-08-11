package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.CrmCustomBankInfoMapper;
import com.tuoyou.tavern.crm.service.CrmCustomBankInfoService;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomBankInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomBankInfoServiceImpl extends ServiceImpl<CrmCustomBankInfoMapper, CrmCustomBankInfo> implements CrmCustomBankInfoService {
}

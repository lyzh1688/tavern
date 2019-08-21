package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.CrmBankInfoMapper;
import com.tuoyou.tavern.crm.service.CrmCustomBankInfoService;
import com.tuoyou.tavern.protocol.crm.model.CrmBankInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomBankInfoServiceImpl extends ServiceImpl<CrmBankInfoMapper, CrmBankInfo> implements CrmCustomBankInfoService {
}
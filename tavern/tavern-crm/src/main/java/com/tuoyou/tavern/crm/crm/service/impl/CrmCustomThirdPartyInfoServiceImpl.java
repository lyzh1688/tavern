package com.tuoyou.tavern.crm.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.crm.dao.CrmThirdPartyInfoMapper;
import com.tuoyou.tavern.crm.crm.service.CrmCustomThirdPartyInfoService;
import com.tuoyou.tavern.protocol.crm.model.CrmThirdPartyInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomThirdPartyInfoServiceImpl extends ServiceImpl<CrmThirdPartyInfoMapper, CrmThirdPartyInfo> implements CrmCustomThirdPartyInfoService {
}

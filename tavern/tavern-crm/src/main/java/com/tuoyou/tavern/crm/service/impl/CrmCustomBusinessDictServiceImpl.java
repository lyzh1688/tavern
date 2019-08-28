package com.tuoyou.tavern.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.dao.crm.CrmBusinessDictMapper;
import com.tuoyou.tavern.crm.service.CrmCustomBusinessDictService;
import com.tuoyou.tavern.protocol.crm.model.CrmBusinessDict;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomBusinessDictServiceImpl extends ServiceImpl<CrmBusinessDictMapper, CrmBusinessDict> implements CrmCustomBusinessDictService {
    @Override
    public List<CrmBusinessDict> getCrmOrderBusinessByOrderId(String orderId) {
        return this.baseMapper.selectCrmOrderBusinessByOrderId(orderId);
    }
}

package com.tuoyou.tavern.crm.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.crm.crm.service.CrmCustomOrderInfoService;
import com.tuoyou.tavern.crm.crm.dao.CrmCustomOrderInfoMapper;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyOrderQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomOrderInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@Service
@AllArgsConstructor
public class CrmCustomOrderInfoServiceImpl extends ServiceImpl<CrmCustomOrderInfoMapper, CrmCustomOrderInfo> implements CrmCustomOrderInfoService {


    @Override
    public void createCrmCustomOrderInfo(CrmCustomOrderInfo crmCustomOrderInfo) {
        this.saveOrUpdate(crmCustomOrderInfo);
    }

    @Override
    public IPage<CrmCustomOrderInfo> getCrmOrderPage(Page page, CustomCompanyOrderQueryDTO customCompanyOrderQueryDTO) {
        return this.baseMapper.selectCrmOrderPage(page, customCompanyOrderQueryDTO);
    }
}

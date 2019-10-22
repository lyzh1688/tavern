package com.tuoyou.tavern.crm.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyOrderQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomOrderInfo;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomOrderInfoVO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
public interface CrmCustomOrderInfoService extends IService<CrmCustomOrderInfo> {

    void createCrmCustomOrderInfo(CrmCustomOrderInfo crmCustomOrderInfo) throws Exception;
    void deleteCrmCustomOrderInfo(String orderId) throws Exception;

    IPage<CrmCustomOrderInfoVO> getCrmOrderPage(Page page, CustomCompanyOrderQueryDTO customCompanyOrderQueryDTO);

}

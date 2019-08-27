package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.service.CrmCustomOrderInfoService;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyOrderQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomOrderInfo;
import com.tuoyou.tavern.protocol.crm.response.CrmCustomOrderPageResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class CustomOrderInfoEndpoint {

    private CrmCustomOrderInfoService crmCustomOrderInfoService;

    @PostMapping("/save")
    public TavernResponse createCrmCustomOrderInfo(@RequestBody @Valid CrmCustomOrderInfo crmCustomOrderInfo) {
        this.crmCustomOrderInfoService.createCrmCustomOrderInfo(crmCustomOrderInfo);
        return new TavernResponse();
    }

    @GetMapping("/page")
    public CrmCustomOrderPageResponse getCrmOrderPage(Page page, CustomCompanyOrderQueryDTO customCompanyOrderQueryDTO) {
        return new CrmCustomOrderPageResponse(this.crmCustomOrderInfoService.getCrmOrderPage(page, customCompanyOrderQueryDTO));
    }




}

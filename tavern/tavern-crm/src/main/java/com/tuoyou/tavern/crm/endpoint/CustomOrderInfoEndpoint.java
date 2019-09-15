package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.crm.service.CrmCustomOrderBusinessRelService;
import com.tuoyou.tavern.crm.crm.service.CrmCustomOrderInfoService;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.dto.CrmOrderBusinessRelDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyOrderQueryDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomOrderBizQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomOrderInfo;
import com.tuoyou.tavern.protocol.crm.response.CrmCompanyOrderDetailResponse;
import com.tuoyou.tavern.protocol.crm.response.CrmCustomOrderPageResponse;
import com.tuoyou.tavern.protocol.crm.response.CrmOrderBusinessPageResponse;
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
    private CrmCustomOrderBusinessRelService crmCustomOrderBusinessRelService;

    @PostMapping("/save")
    public TavernResponse createCrmCustomOrderInfo(@RequestBody @Valid CrmCustomOrderInfo crmCustomOrderInfo) {
        this.crmCustomOrderInfoService.createCrmCustomOrderInfo(crmCustomOrderInfo);
        return new TavernResponse();
    }

    @GetMapping("/page")
    public CrmCustomOrderPageResponse getCrmOrderPage(Page page, CustomCompanyOrderQueryDTO customCompanyOrderQueryDTO) {
        return new CrmCustomOrderPageResponse(this.crmCustomOrderInfoService.getCrmOrderPage(page, customCompanyOrderQueryDTO));
    }

    @PostMapping("/saveBizRel")
    public TavernResponse createCrmCustomOrderInfo(@RequestBody @Valid CrmOrderBusinessRelDTO crmOrderBusinessRelDTO) {
        this.crmCustomOrderBusinessRelService.saveOrderBusinessAndStartWorkFlow(crmOrderBusinessRelDTO);
        return new TavernResponse();
    }

    @GetMapping("/bizPage")
    public CrmOrderBusinessPageResponse getCrmOrderBusinessPage(Page page, CustomOrderBizQueryDTO customCompanyOrderQueryDTO) {
        return new CrmOrderBusinessPageResponse(this.crmCustomOrderBusinessRelService.getCrmOrderBusinessPage(page, customCompanyOrderQueryDTO));
    }

    @GetMapping("/companyDetail")
    public CrmCompanyOrderDetailResponse getCrmCompanyOrderDetail(@RequestParam("eventId")String eventId){
        return new CrmCompanyOrderDetailResponse(this.crmCustomOrderBusinessRelService.getCrmCompanyOrderDetail(eventId));

    }



}

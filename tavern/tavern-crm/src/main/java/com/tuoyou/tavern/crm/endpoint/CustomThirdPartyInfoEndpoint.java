package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.crm.service.CrmCustomThirdPartyInfoService;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.dto.CrmThirdPartyInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomThirdPartyQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmThirdPartyInfo;
import com.tuoyou.tavern.protocol.crm.response.CrmCustomThirdPartyPageResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/10/27 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/thirdParty")
public class CustomThirdPartyInfoEndpoint {

    private final CrmCustomThirdPartyInfoService crmCustomThirdPartyInfoService;

    @PostMapping("/save")
    public TavernResponse createCustomThirdPartyInfo(@RequestBody @Valid CrmThirdPartyInfoDTO crmThirdPartyInfoDTO)  {
        this.crmCustomThirdPartyInfoService.createCrmCustomThirdPartyInfo(crmThirdPartyInfoDTO);
        return new TavernResponse();
    }
    @DeleteMapping("/delete")
    public TavernResponse deleteCustomThirdPartyInfo(@RequestParam("thirdPartyId") String thirdPartyId)  {
        this.crmCustomThirdPartyInfoService.removeById(thirdPartyId);
        return new TavernResponse();
    }

    @GetMapping("/page")
    public CrmCustomThirdPartyPageResponse getCustomThirdPartyInfoPage(Page page, CustomThirdPartyQueryDTO customCompanyOrderQueryDTO) {
        return new CrmCustomThirdPartyPageResponse(this.crmCustomThirdPartyInfoService.queryCustomThirdPartyInfoPage(page,customCompanyOrderQueryDTO));
    }

}

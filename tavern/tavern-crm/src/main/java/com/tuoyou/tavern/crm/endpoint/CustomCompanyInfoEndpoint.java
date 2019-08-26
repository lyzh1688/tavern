package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.service.CrmCustomCompanyInfoService;
import com.tuoyou.tavern.crm.service.CrmCustomCompanyRelService;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyRelDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyQueryDTO;
import com.tuoyou.tavern.protocol.crm.response.CrmCompanyPageResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/company")
public class CustomCompanyInfoEndpoint {

    private CrmCustomCompanyInfoService crmCustomCompanyInfoService;
    private CrmCustomCompanyRelService crmCustomCompanyRelService;


    @PostMapping("/save")
    public TavernResponse createCrmCompanyInfo(@RequestBody @Valid CrmCompanyInfoDTO crmCompanyInfoDTO) {
        this.crmCustomCompanyInfoService.createCrmCompanyInfo(crmCompanyInfoDTO);
        return new TavernResponse();
    }

    @DeleteMapping("/delete")
    public TavernResponse deleteCrmCompanyInfo(@RequestBody List<CrmCompanyRelDTO> crmCompanyRelDTOList) {
        this.crmCustomCompanyRelService.deleteCrmCompanyRelInfo(crmCompanyRelDTOList);
        return new TavernResponse();
    }

    @GetMapping("/page")
    public CrmCompanyPageResponse getCrmCompanyPage(Page page, CustomCompanyQueryDTO customCompanyQueryDTO) {
        return new CrmCompanyPageResponse(this.crmCustomCompanyInfoService.getCompanyInfoPage(page, customCompanyQueryDTO));
    }


}

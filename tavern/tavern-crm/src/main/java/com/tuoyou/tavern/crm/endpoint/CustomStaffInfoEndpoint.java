package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.crm.service.CrmCustomStaffInfoService;
import com.tuoyou.tavern.crm.crm.service.CrmCustomStaffRelService;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.common.model.Dict;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyStaffInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomStaffQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyStaffRel;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyStaffSumVO;
import com.tuoyou.tavern.protocol.crm.response.CrmCompanyPageResponse;
import com.tuoyou.tavern.protocol.crm.response.CrmCompanyStaffSumResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/staff")
public class CustomStaffInfoEndpoint {

    private CrmCustomStaffInfoService crmCustomStaffInfoService;
    private CrmCustomStaffRelService crmCustomStaffRelService;

    @PostMapping("/save")
    public TavernResponse createCrmStaffInfo(@RequestBody @Valid CrmCompanyStaffInfoDTO crmCompanyStaffInfoDTO) {
        this.crmCustomStaffInfoService.createCrmCompanyStaffInfo(crmCompanyStaffInfoDTO);
        return new TavernResponse();
    }

    @DeleteMapping("/delete")
    public TavernResponse deleteCrmStaffInfo(@RequestBody List<CrmCompanyStaffRel> crmCompanyStaffRelList) {
        this.crmCustomStaffRelService.deleteCrmCustomStaffRel(crmCompanyStaffRelList);
        return new TavernResponse();
    }

    @GetMapping("/page")
    public CrmCompanyPageResponse getCrmStaffPage(Page page, CustomStaffQueryDTO customCompanyQueryDTO) {
        return new CrmCompanyPageResponse(this.crmCustomStaffInfoService.getCrmStaffPage(page, customCompanyQueryDTO));
    }

    /**
     * 员工数量
     */
    @GetMapping("/staffSum")
    public CrmCompanyStaffSumResponse getCrmCompanyStaffSumInfo(@RequestParam(name = "companyId") String companyId) {
        int count = this.crmCustomStaffRelService.count(Wrappers.<CrmCompanyStaffRel>query().lambda().eq(CrmCompanyStaffRel::getCompanyId, companyId));
        return new CrmCompanyStaffSumResponse(new CrmCompanyStaffSumVO(count));
    }
}

package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.crm.service.CrmCompanyBusinessInfoService;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.dto.CrmCompanyBusinessRelDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomCompanyBusinessQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCompanyBusiness;
import com.tuoyou.tavern.protocol.crm.response.CrmCompanyBusinessPageResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/business")
public class CustomBusinessInfoEndpoint {

    private CrmCompanyBusinessInfoService crmCompanyBusinessInfoService;


    @GetMapping("/page")
    public CrmCompanyBusinessPageResponse getCrmCompanyPage(Page page, CustomCompanyBusinessQueryDTO customCompanyBusinessQueryDTO) {
        return new CrmCompanyBusinessPageResponse(this.crmCompanyBusinessInfoService.getCompanyBusinessPage(page, customCompanyBusinessQueryDTO));
    }

    @DeleteMapping("/delete")
    public TavernResponse deleteCompanyBusinessRel(@RequestBody List<CrmCompanyBusinessRelDTO> crmCompanyBusinessRelDTOList) {
        List<CrmCompanyBusiness> crmCompanyBusinessList = crmCompanyBusinessRelDTOList.stream()
                .map(biz -> {
                    CrmCompanyBusiness crmCompanyBusiness = new CrmCompanyBusiness();
                    BeanUtils.copyProperties(biz, crmCompanyBusiness);
                    crmCompanyBusiness.setIsValid("0");
                    crmCompanyBusiness.setUpdateDate(LocalDateTime.now());
                    return crmCompanyBusiness;
                }).collect(Collectors.toList());
        this.crmCompanyBusinessInfoService.updateBatchById(crmCompanyBusinessList);
        return new TavernResponse();
    }

}

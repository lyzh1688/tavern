package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.crm.service.CrmCustomBasicInfoService;
import com.tuoyou.tavern.crm.crm.service.CrmCustomOrderInfoService;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.dto.CustomInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomBasicInfo;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomOrderInfo;
import com.tuoyou.tavern.protocol.crm.model.CrmOrderBusinessRel;
import com.tuoyou.tavern.protocol.crm.response.CustomInfoPageResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/info")
public class CustomInfoEndpoint {

    private final CrmCustomBasicInfoService crmCustomBasicInfoService;
    private final CrmCustomOrderInfoService crmCustomOrderInfoService;

    /**
     * 新增或修改客户
     */
    @PostMapping("/save")
    public TavernResponse createCustom(@Valid @RequestBody CustomInfoDTO customInfoDTO) {
        this.crmCustomBasicInfoService.createCustom(customInfoDTO);
        return new TavernResponse();
    }

    /**
     * 删除客户
     */
    @DeleteMapping("/delete")
    public TavernResponse deleteCustom(@RequestBody List<String> customIdList) throws Exception {
        if (!customIdList.isEmpty()) {
            for (String customerId : customIdList) {
                int count = this.crmCustomOrderInfoService.count(Wrappers.<CrmCustomOrderInfo>query().lambda()
                        .eq(CrmCustomOrderInfo::getCustomId, customerId));
                if (count != 0) {
                    throw new Exception("该客户尚有关联订单，不可删除！");
                }
            }
            List<CrmCustomBasicInfo> crmCustomBasicInfoList = customIdList.stream()
                    .map(info -> {
                        CrmCustomBasicInfo crmCustomBasicInfo = new CrmCustomBasicInfo();
                        crmCustomBasicInfo.setCustomId(info);
                        crmCustomBasicInfo.setUpdateDate(LocalDateTime.now());
                        crmCustomBasicInfo.setIsValid("0");
                        return crmCustomBasicInfo;
                    }).collect(Collectors.toList());

            this.crmCustomBasicInfoService.updateBatchById(crmCustomBasicInfoList);
        }
        return new TavernResponse();
    }

    /**
     * 查询客户
     */
    @GetMapping("/page")
    public CustomInfoPageResponse getCustomInfoPage(Page page, CustomQueryDTO customQueryDTO) {
        return new CustomInfoPageResponse(this.crmCustomBasicInfoService.getBasicInfoPage(page, customQueryDTO));
    }

    /**
     * 登录查询用户
     *//*
    @GetMapping("/info")
    public CustomInfoResponse getCustomInfo(@PathVariable("accnt") String accnt, @PathVariable("password") String password) {
        CustomBasicInfoVO customBasicInfoVO = this.crmCustomBasicInfoService.getBasicInfo(accnt, password);
        CustomInfoResponse customInfoResponse = new CustomInfoResponse();
        if (Objects.isNull(customBasicInfoVO)) {
            customInfoResponse.setRetCode(RetCode.AUTH_FAILED);
            customInfoResponse.setRetMessage("用户不存在");
            return customInfoResponse;
        } else {
            customInfoResponse.setData(customBasicInfoVO);
            return customInfoResponse;
        }
    }*/


}

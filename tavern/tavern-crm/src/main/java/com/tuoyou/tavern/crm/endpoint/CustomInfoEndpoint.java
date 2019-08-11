package com.tuoyou.tavern.crm.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.crm.service.CrmCustomBasicInfoService;
import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.crm.dto.CustomInfoDTO;
import com.tuoyou.tavern.protocol.crm.dto.CustomQueryDTO;
import com.tuoyou.tavern.protocol.crm.model.CrmCustomBasicInfo;
import com.tuoyou.tavern.protocol.crm.model.CustomBasicInfoVO;
import com.tuoyou.tavern.protocol.crm.response.CustomInfoPageResponse;
import com.tuoyou.tavern.protocol.crm.response.CustomInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/crm")
public class CustomInfoEndpoint {

    private final CrmCustomBasicInfoService crmCustomBasicInfoService;

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
    @DeleteMapping("delete")
    public TavernResponse deleteCustom(List<String> customIdList) {
        if (!customIdList.isEmpty()) {
            List<CrmCustomBasicInfo> crmCustomBasicInfoList = customIdList.stream()
                    .map(info -> {
                        CrmCustomBasicInfo crmCustomBasicInfo = new CrmCustomBasicInfo();
                        crmCustomBasicInfo.setCustomId(info);
                        crmCustomBasicInfo.setIsValid("0");
                        crmCustomBasicInfo.setUpdateDate(LocalDateTime.now());
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
     */
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
    }


}

package com.tuoyou.tavern.crm.endpoint;

import com.tuoyou.tavern.crm.service.HrmUserBasicInfoService;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;
import com.tuoyou.tavern.protocol.common.model.Dict;
import com.tuoyou.tavern.protocol.hrm.model.HrmUserBasicInfo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 功能说明: <br>
 * 系统说明: <br>
 * 模块说明: <br>
 * 功能描述: <br>
 * <br>
 * 开发人员: Hebiao <br>
 * 开发时间: 2019/8/28 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/hrm/dict")
public class StaffDictEndpoint {

    private HrmUserBasicInfoService hrmUserBasicInfoService;

    /**
     * 根据角色查询用户字典
     */
    @GetMapping("/findDictByRole")
    public TavernDictResponse queryStaffByRole(@RequestParam(name = "roleId") String roleId) {
        List<HrmUserBasicInfo> hrmUserBasicInfoList = this.hrmUserBasicInfoService
                .getStaffByRole(roleId);
        List<Dict> dictList = hrmUserBasicInfoList.parallelStream()
                .map(info -> {
                    Dict dict = new Dict();
                    dict.setId(info.getUserId());
                    dict.setName(info.getUserName());
                    return dict;
                }).collect(Collectors.toList());
        return new TavernDictResponse(dictList);
    }
}

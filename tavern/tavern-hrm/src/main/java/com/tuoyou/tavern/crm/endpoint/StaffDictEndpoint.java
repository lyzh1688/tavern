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
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
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

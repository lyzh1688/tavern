package com.tuoyou.tavern.crm.endpoint;

import com.tuoyou.tavern.crm.crm.service.CrmCustomBusinessDictService;
import com.tuoyou.tavern.crm.crm.service.CrmCustomCompanyInfoService;
import com.tuoyou.tavern.crm.crm.service.CrmCustomThirdPartyInfoService;
import com.tuoyou.tavern.crm.workflow.service.WorkFlowDefNodeService;
import com.tuoyou.tavern.protocol.common.TavernDictResponse;
import com.tuoyou.tavern.protocol.common.model.Dict;
import com.tuoyou.tavern.protocol.hrm.spi.HrmUserDictService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dict")
public class CustomDictInfoEndpoint {

    private CrmCustomBusinessDictService crmCustomBusinessDictService;
    private CrmCustomCompanyInfoService crmCustomCompanyInfoService;
    private HrmUserDictService hrmUserDictService;
    private WorkFlowDefNodeService workFlowDefNodeService;
    private CrmCustomThirdPartyInfoService crmCustomThirdPartyInfoService;


    /**
     * 业务字典
     */
    @GetMapping("/business")
    public TavernDictResponse getCrmBusinessDict() {
        List<Dict> dictList = this.crmCustomBusinessDictService.list()
                .parallelStream()
                .map(d -> {
                    Dict dict = new Dict();
                    dict.setId(d.getBusinessId());
                    dict.setName(d.getBusinessName());
                    return dict;
                }).collect(Collectors.toList());
        return new TavernDictResponse(dictList);
    }

    /**
     * 关联公司
     */
    @GetMapping("/company")
    public TavernDictResponse getCrmCompanyDict(@RequestParam(name = "customId") String customId) {
        List<Dict> dictList = this.crmCustomCompanyInfoService.getCompanyByCustomId(customId)
                .parallelStream()
                .map(d -> {
                    Dict dict = new Dict();
                    dict.setId(d.getCompanyId());
                    dict.setName(d.getCompanyName());
                    return dict;
                }).collect(Collectors.toList());
        return new TavernDictResponse(dictList);
    }

    /**
     * 对接人员
     */
    @GetMapping("/owner")
    public TavernDictResponse getWorkFlowPointOperator(@RequestParam(name = "business") String business) {
        //添加关联业务后，即发起一个流程，根据节点角色进行选择
        //1.graph代表UML
        //2.edge代表分支
        //3.node代表节点
        //4.role代表角色
        //1.传入节点角色
        //2.根据角色进行查询人员
        //3.rpc到hrm查询相关人员
        return this.workFlowDefNodeService.getWorkFlowOwnerInfo(business);
    }

    /**
     * 第三方合作方
     */
    @GetMapping("/thirdParty")
    public TavernDictResponse getThirdParty() {
        List<Dict> dictList = this.crmCustomThirdPartyInfoService.list()
                .parallelStream()
                .map(d -> {
                    Dict dict = new Dict();
                    dict.setId(d.getThirdPartyId());
                    dict.setName(d.getThirdPartyName());
                    return dict;
                }).collect(Collectors.toList());
        return new TavernDictResponse(dictList);
    }

    /**
     * 前置任务
     */
    @GetMapping("/preEvent")
    public TavernDictResponse getCrmPreEventDict(@RequestParam(name = "orderId") String orderId) {
        List<Dict> dictList = this.crmCustomBusinessDictService.getCrmOrderBusinessByOrderId(orderId)
                .parallelStream()
                .map(d -> {
                    Dict dict = new Dict();
                    dict.setId(d.getBusinessId());
                    dict.setName(d.getBusinessName());
                    return dict;
                }).collect(Collectors.toList());
        return new TavernDictResponse(dictList);
    }
}

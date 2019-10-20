package com.tuoyou.tavern.protocol.crm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/31 <br>
 */
@Data
public class CrmOrderBusinessRelDTO {
    @NotNull(message = "orderId不能为空")
    private String orderId;
    @NotNull(message = "businessId不能为空")
    private String businessId;

    private String companyId;

    private String creatorId;//记录录单点
    private String creatorName;//记录录单点

    private String ownerId;

    private String owner;

    private String needThirdParty;

    private String thirdPartyId;

    private BigDecimal thirdPartyFee;

    private String businessTag;

    private String preEventId;

    private String remark;

    //代理记账
    private DljzDetail dljzDetail;

    //公积金代缴/社保代缴
    private GjjsbdjDetail gjjsbdjDetail;

    //公司注册
    private GszcDetail gszcDetail;

    private String eventId;

    private String curNode;
}

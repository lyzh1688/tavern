package com.tuoyou.tavern.protocol.crm.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class CrmThirdPartyInfoDTO {
    private String thirdPartyId;

    private String thirdPartyName;

    private List<String> area = Lists.newArrayList();

    private String address;

    private String deliveryAddr;

    private String contact1;

    private String contactNumber1;

    private String contactWechat1;

    private String contact2;

    private String contactNumber2;

    private String contactWechat2;


}
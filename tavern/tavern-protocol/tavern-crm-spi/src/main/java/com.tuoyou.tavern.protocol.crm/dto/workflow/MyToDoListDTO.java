package com.tuoyou.tavern.protocol.crm.dto.workflow;

import lombok.Data;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/04 <br>
 */
@Data
public class MyToDoListDTO {
    private String userId;
    private String city;
    private String district;
    private String companyName;
    private String customName;
    private String customLevel;
    private String curNodeName;
    private String weixinName;
    private String createDate;
    private String businessName;
    private String businessTag;
    private Boolean ifOver;
}

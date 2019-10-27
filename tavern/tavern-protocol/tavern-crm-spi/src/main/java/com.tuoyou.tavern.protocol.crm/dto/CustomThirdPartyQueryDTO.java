package com.tuoyou.tavern.protocol.crm.dto;

import lombok.Data;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/25 <br>
 */
@Data
public class CustomThirdPartyQueryDTO {
    private String thirdPartyName;
    private String province;
    private String city;
    private String district;
}

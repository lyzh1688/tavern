package com.tuoyou.tavern.protocol.crm.model;

import lombok.Data;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/15 <br>
 */
@Data
public class CrmCompanyOrderDetailVO {
    private String orderId;
    private String receivableAmt;
    private String companyName;
    private String customName;
    private String district;
    private String contactNumber;
    private String wangwangAccnt;
    private String employeeNum;
    private String beginDate;
    private String endDate;

}

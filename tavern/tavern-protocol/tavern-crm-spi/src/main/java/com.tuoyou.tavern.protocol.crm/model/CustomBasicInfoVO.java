package com.tuoyou.tavern.protocol.crm.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Data
public class CustomBasicInfoVO {
    private String customId;

    private String customAccnt;

    private String customName;

    private String password;

    private String roleId;

    private String contactName;

    private String contactTel;

    private String bankId;

    private String bankName;

    private String financeDiskType;

    private String updateDate;


}

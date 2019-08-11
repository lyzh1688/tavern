package com.tuoyou.tavern.protocol.crm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Data
public class CustomInfoDTO {
    @NotNull
    private String customId;

    @NotNull
    private String customAccnt;

    private String customName;

    @NotNull
    private String password;

    private String contactName;

    private String contactTel;

    private String bankId;

    private String bankName;

    private String financeDiskType;



}

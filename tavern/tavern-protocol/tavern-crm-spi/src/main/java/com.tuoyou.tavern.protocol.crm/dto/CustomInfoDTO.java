package com.tuoyou.tavern.protocol.crm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Data
public class CustomInfoDTO {
    private String customId;
    @NotNull
    private String weixinAccnt;

    private String weixinName;
    @NotNull
    private String wangwangAccnt;
    @NotNull
    private String contactPerson;
    @NotNull
    private String contactNumber;

    private String corporation;

    private String corporationNumber;
    @NotNull
    private String customLevel;
    @NotNull
    private String customName;

    private String updateDate;



}

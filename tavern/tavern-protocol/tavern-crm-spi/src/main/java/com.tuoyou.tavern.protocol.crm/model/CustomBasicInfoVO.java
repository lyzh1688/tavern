package com.tuoyou.tavern.protocol.crm.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
@Data
public class CustomBasicInfoVO {
    private String customId;

    private String weixinAccnt;

    private String weixinName;

    private String wangwangAccnt;

    private String contactPerson;

    private String contactNumber;

    private String corporation;

    private String corporationNumber;

    private String customLevel;

    private String customName;

    private String updateDate;

    private String companyName;


}

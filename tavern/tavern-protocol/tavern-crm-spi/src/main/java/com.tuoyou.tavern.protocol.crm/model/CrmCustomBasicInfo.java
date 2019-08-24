package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_CUSTOM_BASIC_INFO")
public class CrmCustomBasicInfo  extends Model<CrmCustomBasicInfo> {
    @TableId
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

    private String isValid;

    private LocalDateTime updateDate;

}
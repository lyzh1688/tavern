package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_CUSTOM_BASIC_INFO")
public class CrmCustomBasicInfo  extends Model<CrmCustomBasicInfo> {
    @TableId
    private String customId;

    private String customAccnt;

    private String customName;

    private String password;

    private String contactName;

    private String contactTel;

    private String isValid;

    private LocalDateTime updateDate;
}
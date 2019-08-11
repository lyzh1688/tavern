package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_CUSTOM_FINANCE_INFO")
public class CrmCustomFinanceInfo extends Model<CrmCustomFinanceInfo> {
    private String customId;

    private String financeDiskType;

    private LocalDateTime updateDate;

}
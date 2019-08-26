package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_COMPANY_STAFF_INFO")
public class CrmCompanyStaffInfo extends Model<CrmCompanyStaffInfo> {
    @TableId
    private String staffId;

    private String idCard;

    private String name;

    private String contactNumber;

    private String needSocialIns;

    private String needHabitationIns;

    private String needSocialServer;

    private String needHabitationServer;

    private LocalDateTime updateDate;
}
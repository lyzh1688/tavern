package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_ORDER_GJJSBDJ_DETAIL")
public class CrmOrderGjjsbdjDetail extends Model<CrmOrderGjjsbdjDetail> {
    @TableId
    private String eventId;

    private String orderId;

    private String businessId;

    private LocalDate beginDate;

    private LocalDate endDate;

    private Integer employeeNum;

}
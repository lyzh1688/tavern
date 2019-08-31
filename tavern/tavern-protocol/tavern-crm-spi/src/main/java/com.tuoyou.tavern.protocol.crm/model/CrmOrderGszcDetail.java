package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_ORDER_GSZC_DETAIL")
public class CrmOrderGszcDetail extends Model<CrmOrderGszcDetail> {
    private String eventId;

    private String orderId;

    private String absent;

    private String regLocationType;

}
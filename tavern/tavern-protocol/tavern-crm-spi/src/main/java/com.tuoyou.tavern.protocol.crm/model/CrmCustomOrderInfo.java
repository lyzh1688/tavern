package com.tuoyou.tavern.protocol.crm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("CRM_CUSTOM_ORDER_INFO")
public class CrmCustomOrderInfo extends Model<CrmCustomOrderInfo> {
    @TableId
    private String orderId;

    private String customId;

    private String orderDate;

    private BigDecimal receivableAmt;

    private BigDecimal payableAmt;

}
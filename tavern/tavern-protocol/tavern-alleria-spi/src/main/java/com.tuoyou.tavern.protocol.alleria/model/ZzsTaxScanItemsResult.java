package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ALR_VALUE_ADDED_TAX_SCAN_ITEMS_RESULT")
public class ZzsTaxScanItemsResult extends Model<ZzsTaxScanItemsResult> {
    private String invoiceId;

    private String invoiceCode;

    private String itemName;

    private String itemSpec;

    private String itemUnit;

    private String itemAmount;

    private String priceUnit;

    private String priceSum;

    private String taxRate;

    private String taxSum;

}
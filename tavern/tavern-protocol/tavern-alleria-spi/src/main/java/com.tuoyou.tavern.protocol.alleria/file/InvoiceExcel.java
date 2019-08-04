package com.tuoyou.tavern.protocol.alleria.file;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/21 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InvoiceExcel extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String invoiceId;
    @ExcelProperty(index = 1)
    private String invoiceCode;
    @ExcelProperty(index = 2)
    private String buyerName;
    @ExcelProperty(index = 3)
    private String buyerTaxCode;
    @ExcelProperty(index = 4)
    private String bankBranch;
    @ExcelProperty(index = 4)
    private String bankAccount;
    @ExcelProperty(index = 5)
    private String address;
    @ExcelProperty(index = 5)
    private String tel;
    @ExcelProperty(index = 6)
    private String invoiceDate;
    @ExcelProperty(index = 7)
    private String productVersion;
    @ExcelProperty(index = 8)
    private String receiptId;
    @ExcelProperty(index = 9)
    private String productName;
    @ExcelProperty(index = 10)
    private String productSpec;
    @ExcelProperty(index = 11)
    private String productUnit;
    @ExcelProperty(index = 12)
    private String productCount;
    @ExcelProperty(index = 13)
    private String productUnitPrice;
    @ExcelProperty(index = 14)
    private String totalPrice;
    @ExcelProperty(index = 15)
    private String taxRate;
    @ExcelProperty(index = 16)
    private String tax;
    @ExcelProperty(index = 17)
    private String taxTypeCode;
}

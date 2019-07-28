package com.tuoyou.tavern.protocol.alleria.file;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/25 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BankStatementExcel  extends BaseRowModel {


    @ExcelProperty(index = 0)
    private String customAccntId;
    @ExcelProperty(index = 1)
    private String customAccntName;
    @ExcelProperty(index = 2)
    private String tradeDate;
    @ExcelProperty(index = 3)
    private String debitAmount;
    @ExcelProperty(index = 4)
    private String creditAmount;
    @ExcelProperty(index = 5)
    private String balance;
    @ExcelProperty(index = 6)
    private String coinCode;
    @ExcelProperty(index = 7)
    private String recvAccntId;
    @ExcelProperty(index = 8)
    private String recvAccntName;
    @ExcelProperty(index = 9)
    private String institution;
    @ExcelProperty(index =10)
    private String accountingDate;
    @ExcelProperty(index =11)
    private String abstracts;
    @ExcelProperty(index =12)
    private String remark;
    @ExcelProperty(index = 13)
    private String detailId;
    @ExcelProperty(index = 14)
    private String companySeq;
    @ExcelProperty(index = 15)
    private String certificateType;
    @ExcelProperty(index = 16)
    private String certificateId;
}

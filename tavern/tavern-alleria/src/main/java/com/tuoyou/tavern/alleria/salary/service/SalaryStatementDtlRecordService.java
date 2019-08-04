package com.tuoyou.tavern.alleria.salary.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.alleria.dto.SalaryStatementRecordDTO;
import com.tuoyou.tavern.protocol.alleria.dto.StdInvoiceRecordDTO;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementDtlRecordVO;
import com.tuoyou.tavern.protocol.alleria.model.StdInvoiceDtlRecordVO;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/13 <br>
 */
public interface SalaryStatementDtlRecordService extends IService<SalaryStatementDtlRecord> {

    IPage<SalaryStatementDtlRecordVO> getSalaryStatementDtlRecord(Page page, SalaryStatementRecordDTO salaryStatementRecordDTO);

}

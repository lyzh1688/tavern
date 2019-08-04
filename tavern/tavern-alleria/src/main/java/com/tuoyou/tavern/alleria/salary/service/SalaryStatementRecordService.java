package com.tuoyou.tavern.alleria.salary.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.protocol.alleria.dto.SalaryStatementRecordDTO;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementRecord;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementRecordVO;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/13 <br>
 */
public interface SalaryStatementRecordService extends IService<SalaryStatementRecord> {

    void parseSalaryStatement(FileTransfer fileTransfer);

    void updateStatus(String batchId, String valid);

    IPage<SalaryStatementRecordVO> getSalaryStatementRecord(Page page, SalaryStatementRecordDTO salaryStatementRecordDTO);
}

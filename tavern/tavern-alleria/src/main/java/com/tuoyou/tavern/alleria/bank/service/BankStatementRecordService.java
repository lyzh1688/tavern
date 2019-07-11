package com.tuoyou.tavern.alleria.bank.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementRecord;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/10 <br>
 */
public interface BankStatementRecordService extends IService<BankStatementRecord> {
    IPage<BankStatementRecord> getBankStatementRecord(Page page, BankStatementRecord bankStatementQryInfo);

}

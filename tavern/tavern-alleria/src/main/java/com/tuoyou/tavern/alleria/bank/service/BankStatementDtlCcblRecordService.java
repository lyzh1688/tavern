package com.tuoyou.tavern.alleria.bank.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDtlQryInfo;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementDtlCcblRecord;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/10 <br>
 */
public interface BankStatementDtlCcblRecordService extends IService<BankStatementDtlCcblRecord> {

    IPage<BankStatementDtlCcblRecord> getBankStatementDtlRecord(Page page, BankStatementDtlQryInfo bankStatementDtlQryInfo);

    List<String> getRecvAccntIdDict(String batchId, String recvAccntId);
}

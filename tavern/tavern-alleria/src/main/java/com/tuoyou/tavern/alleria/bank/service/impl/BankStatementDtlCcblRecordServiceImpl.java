package com.tuoyou.tavern.alleria.bank.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.bank.dao.BankStatementDtlCcblRecordMapper;
import com.tuoyou.tavern.alleria.bank.service.BankStatementDtlCcblRecordService;
import com.tuoyou.tavern.alleria.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDtlQryInfo;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementDtlCcblRecord;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/10 <br>
 */
@Service
public class BankStatementDtlCcblRecordServiceImpl extends ServiceImpl<BankStatementDtlCcblRecordMapper, BankStatementDtlCcblRecord> implements BankStatementDtlCcblRecordService {


    @Override
    public IPage getBankStatementDtlRecord(Page page, BankStatementDtlQryInfo bankStatementDtlQryInfo) {
        return this.baseMapper.selectPage(page, Wrappers.<BankStatementDtlCcblRecord>query().lambda()
                .between(BankStatementDtlCcblRecord::getTradeDate, DateUtils.parseDateTime(bankStatementDtlQryInfo.getStartTradeDate(), DateUtils.DEFAULT_DATETIME_FORMATTER), DateUtils.parseDateTime(bankStatementDtlQryInfo.getEndTradeDate(), DateUtils.DEFAULT_DATETIME_FORMATTER))
                .eq(BankStatementDtlCcblRecord::getRecvAccntId, bankStatementDtlQryInfo.getRecvAccntId()));
    }
}

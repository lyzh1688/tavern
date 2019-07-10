package com.tuoyou.tavern.alleria.bank.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.bank.dao.BankStatementRecordMapper;
import com.tuoyou.tavern.alleria.bank.service.BankStatementRecordService;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementRecord;
import org.springframework.stereotype.Service;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/10 <br>
 */
@Service
public class BankStatementRecordServiceImpl extends ServiceImpl<BankStatementRecordMapper, BankStatementRecord> implements BankStatementRecordService {


    @Override
    public IPage getBankStatementRecord(Page page, BankStatementRecord bankStatementRecord) {
        return this.baseMapper.selectPage(page, Wrappers.<BankStatementRecord>query(bankStatementRecord));
    }

}

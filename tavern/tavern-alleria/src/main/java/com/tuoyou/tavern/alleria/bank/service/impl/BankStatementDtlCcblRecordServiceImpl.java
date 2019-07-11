package com.tuoyou.tavern.alleria.bank.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.bank.dao.BankStatementDtlCcblRecordMapper;
import com.tuoyou.tavern.alleria.bank.service.BankStatementDtlCcblRecordService;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDtlDTO;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementDtlCcblRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/10 <br>
 */
@Service
public class BankStatementDtlCcblRecordServiceImpl extends ServiceImpl<BankStatementDtlCcblRecordMapper, BankStatementDtlCcblRecord> implements BankStatementDtlCcblRecordService {


    @Override
    public IPage<BankStatementDtlCcblRecord> getBankStatementDtlRecord(Page page, BankStatementDtlDTO bankStatementDtlDTO) {
        return this.baseMapper.selectBankStatementDtlRecord(page, bankStatementDtlDTO);
    }

    @Override
    public List<String> getRecvAccntIdDict(String batchId, String recvAccntId) {
        return this.baseMapper.selectRecvAccntIdDict(batchId, recvAccntId);
    }
}

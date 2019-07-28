package com.tuoyou.tavern.alleria.bank.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.bank.dao.BankStatementDtlCcblRecordMapper;
import com.tuoyou.tavern.alleria.bank.service.BankStatementDtlCcblRecordService;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDtlDTO;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementDtlCcblRecord;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementDtlCcblRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/10 <br>
 */
@Service
public class BankStatementDtlCcblRecordServiceImpl extends ServiceImpl<BankStatementDtlCcblRecordMapper, BankStatementDtlCcblRecord> implements BankStatementDtlCcblRecordService {


    @Override
    public IPage<BankStatementDtlCcblRecordVO> getBankStatementDtlRecord(Page page, BankStatementDtlDTO bankStatementDtlDTO) {

        IPage<BankStatementDtlCcblRecord> bankStatementDtlCcblRecordIPage = this.baseMapper.selectBankStatementDtlRecord(page, bankStatementDtlDTO);
        List<BankStatementDtlCcblRecordVO> bankStatementRecordVOList = bankStatementDtlCcblRecordIPage.getRecords()
                .stream()
                .map(record -> {
                    BankStatementDtlCcblRecordVO bankStatementDtlCcblRecordVO = new BankStatementDtlCcblRecordVO();
                    BeanUtils.copyProperties(record, bankStatementDtlCcblRecordVO);
                    bankStatementDtlCcblRecordVO.setAccountingDate(DateUtils.formatDateTime(record.getAccountingDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    bankStatementDtlCcblRecordVO.setTradeDate(DateUtils.formatDateTime(record.getTradeDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    return bankStatementDtlCcblRecordVO;
                }).collect(Collectors.toList());
        Page<BankStatementDtlCcblRecordVO> bankStatementDtlCcblRecordVOPage = new Page<>();
        bankStatementDtlCcblRecordVOPage.setRecords(bankStatementRecordVOList);
        bankStatementDtlCcblRecordVOPage.setCurrent(bankStatementDtlCcblRecordIPage.getCurrent());
        bankStatementDtlCcblRecordVOPage.setSize(bankStatementDtlCcblRecordIPage.getSize());
        bankStatementDtlCcblRecordVOPage.setCurrent(bankStatementDtlCcblRecordIPage.getCurrent());
        bankStatementDtlCcblRecordVOPage.setTotal(bankStatementDtlCcblRecordIPage.getTotal());

        return bankStatementDtlCcblRecordVOPage;
    }

    @Override
    public List<BankStatementDtlCcblRecord> getRecvAccntIdDict(String batchId, String recvAccntId) {
        return this.baseMapper.selectRecvAccntIdDict(batchId, recvAccntId);
    }
}

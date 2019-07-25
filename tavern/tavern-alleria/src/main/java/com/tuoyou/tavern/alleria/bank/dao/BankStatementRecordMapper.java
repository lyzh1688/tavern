package com.tuoyou.tavern.alleria.bank.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDTO;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDtlDTO;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementRecord;
import org.apache.ibatis.annotations.Param;

public interface BankStatementRecordMapper extends BaseMapper<BankStatementRecord> {
    IPage<BankStatementRecord> selectBankStatementRecord(Page page, @Param("query") BankStatementDTO bankStatementDTO);
}
package com.tuoyou.tavern.alleria.bank.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDtlDTO;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementDtlCcblRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankStatementDtlCcblRecordMapper extends BaseMapper<BankStatementDtlCcblRecord> {

    IPage<BankStatementDtlCcblRecord> selectBankStatementDtlRecord(Page page, @Param("query") BankStatementDtlDTO bankStatementDtlDTO);

    List<BankStatementDtlCcblRecord> selectRecvAccntIdDict(@Param("batchId") String batchId, @Param("recvAccntId") String recvAccntId);

}
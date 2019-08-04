package com.tuoyou.tavern.alleria.salary.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.dto.SalaryStatementRecordDTO;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementRecord;
import org.apache.ibatis.annotations.Param;

public interface SalaryStatementRecordMapper extends BaseMapper<SalaryStatementRecord> {

    IPage<SalaryStatementRecord> selectSalaryStatementRecord(Page page, @Param("query") SalaryStatementRecordDTO salaryStatementRecordDTO);

    void updateFileStatus(@Param("batchId") String batchId, @Param("valid") String valid);

}
package com.tuoyou.tavern.alleria.salary.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.dto.SalaryStatementRecordDTO;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementDtlRecord;
import org.apache.ibatis.annotations.Param;

public interface SalaryStatementDtlRecordMapper extends BaseMapper<SalaryStatementDtlRecord> {

    IPage<SalaryStatementDtlRecord> selectSalaryStatementDtlRecord(Page page, @Param("query") SalaryStatementRecordDTO salaryStatementRecordDTO);

}
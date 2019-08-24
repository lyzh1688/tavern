package com.tuoyou.tavern.alleria.salary.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.salary.dao.SalaryStatementDtlRecordMapper;
import com.tuoyou.tavern.alleria.salary.service.SalaryStatementDtlRecordService;
import com.tuoyou.tavern.common.core.util.CommonUtils;
import com.tuoyou.tavern.protocol.alleria.dto.SalaryStatementRecordDTO;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementDtlRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/13 <br>
 */
@Service
public class SalaryStatementDtlRecordServiceImpl extends ServiceImpl<SalaryStatementDtlRecordMapper, SalaryStatementDtlRecord> implements SalaryStatementDtlRecordService {
    @Override
    public IPage<SalaryStatementDtlRecordVO> getSalaryStatementDtlRecord(Page page, SalaryStatementRecordDTO salaryStatementRecordDTO) {
        IPage<SalaryStatementDtlRecord> salaryStatementDtlRecordIPage = this.baseMapper.selectSalaryStatementDtlRecord(page, salaryStatementRecordDTO);
        List<SalaryStatementDtlRecordVO> salaryStatementDtlRecordVOList = salaryStatementDtlRecordIPage.getRecords()
                .stream()
                .map(record -> {
                    SalaryStatementDtlRecordVO salaryStatementDtlRecordVO = new SalaryStatementDtlRecordVO();
                    BeanUtils.copyProperties(record, salaryStatementDtlRecordVO);
                    return salaryStatementDtlRecordVO;
                }).collect(Collectors.toList());
        return CommonUtils.newIPage(salaryStatementDtlRecordIPage, salaryStatementDtlRecordVOList);
    }
}

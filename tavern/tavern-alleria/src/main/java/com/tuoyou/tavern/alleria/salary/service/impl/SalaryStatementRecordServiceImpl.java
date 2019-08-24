package com.tuoyou.tavern.alleria.salary.service.impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.tuoyou.tavern.alleria.configuration.TTLContext;
import com.tuoyou.tavern.alleria.salary.dao.SalaryStatementRecordMapper;
import com.tuoyou.tavern.alleria.salary.service.SalaryStatementDtlRecordService;
import com.tuoyou.tavern.alleria.salary.service.SalaryStatementRecordService;
import com.tuoyou.tavern.alleria.util.FileUtils;
import com.tuoyou.tavern.common.core.util.CommonUtils;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.invoice.common.libs.excel.ExcelListener;
import com.tuoyou.tavern.protocol.alleria.dto.SalaryStatementRecordDTO;
import com.tuoyou.tavern.protocol.alleria.file.SalaryExcel;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementRecord;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementRecordVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/13 <br>
 */
@Slf4j
@Service
public class SalaryStatementRecordServiceImpl extends ServiceImpl<SalaryStatementRecordMapper, SalaryStatementRecord> implements SalaryStatementRecordService {

    @Autowired
    private TTLContext ttlContext;
    @Autowired
    private SalaryStatementDtlRecordService salaryStatementDtlRecordService;

    @Override
    public void parseSalaryStatement(FileTransfer fileTransfer) {
        String destLocation = fileTransfer.getDestLocation();
        String batchId = fileTransfer.getBatchId();
        File[] files = new File(destLocation).listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            List<SalaryExcel> salaryExcelList;
            ExcelListener<SalaryExcel> listener = new ExcelListener<SalaryExcel>();
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String pattern = StringUtils.substringAfterLast(file.getName(), ".");
            Sheet sheet = new Sheet(2, 2, SalaryExcel.class);
            sheet.setSheetName("工资计算");
            if (pattern.equals("xls")) {
                (new ExcelReader(inputStream, ExcelTypeEnum.XLS, (Object) null, listener)).read(sheet);
            } else {
                (new ExcelReader(inputStream, (Object) null, listener)).read(sheet);
            }
            salaryExcelList = listener.getDatas();

            //10条一组
            List<List<SalaryExcel>> partList = Lists.partition(salaryExcelList, 10);
            for (int j = 0; j < partList.size(); j++) {
                List<SalaryExcel> list = partList.get(j).stream().filter(item -> StringUtils.isNoneEmpty(item.getStaffId())).collect(Collectors.toList());
                List<SalaryStatementRecord> salaryStatementRecordList = Lists.newArrayList();
                List<SalaryStatementDtlRecord> salaryStatementDtlRecordList = Lists.newArrayList();
                for (SalaryExcel dtl : list) {
                    String fileId = UUIDUtil.randomUUID32();
                    SalaryStatementRecord salaryStatementRecord = new SalaryStatementRecord();
                    salaryStatementRecord.setFileId(fileId);
                    salaryStatementRecord.setBatchId(batchId);
                    salaryStatementRecord.setFileName(file.getName());
                    salaryStatementRecord.setAccountPeriod(LocalDateTime.now());
                    salaryStatementRecord.setIsValid("1");
                    salaryStatementRecord.setUpdateDate(LocalDateTime.now());
                    salaryStatementRecord.setCustomId(dtl.getStaffId());
                    salaryStatementRecordList.add(salaryStatementRecord);

                    SalaryStatementDtlRecord salaryStatementDtlRecord = new SalaryStatementDtlRecord();
                    BeanUtils.copyProperties(dtl, salaryStatementDtlRecord);
                    salaryStatementDtlRecord.setFileId(fileId);
                    salaryStatementDtlRecord.setBaseSalary(StringUtils.isEmpty(dtl.getBaseSalary()) ? null : new BigDecimal(StringUtils.replace(dtl.getBaseSalary(), "￥", "")));
                    salaryStatementDtlRecord.setPositionValue(StringUtils.isEmpty(dtl.getPositionValue()) ? null : new BigDecimal(StringUtils.replace(dtl.getPositionValue(), "￥", "")));
                    salaryStatementDtlRecord.setTravelAllowance(StringUtils.isEmpty(dtl.getTravelAllowance()) ? null : new BigDecimal(StringUtils.replace(dtl.getTravelAllowance(), "￥", "")));
                    salaryStatementDtlRecord.setBusinessAllowance(StringUtils.isEmpty(dtl.getBusinessAllowance()) ? null : new BigDecimal(StringUtils.replace(dtl.getBusinessAllowance(), "￥", "")));
                    salaryStatementDtlRecord.setTelephoneAllowance(StringUtils.isEmpty(dtl.getTelephoneAllowance()) ? null : new BigDecimal(StringUtils.replace(dtl.getTelephoneAllowance(), "￥", "")));
                    salaryStatementDtlRecord.setTaxiAllowance(StringUtils.isEmpty(dtl.getTaxiAllowance()) ? null : new BigDecimal(StringUtils.replace(dtl.getTaxiAllowance(), "￥", "")));
                    salaryStatementDtlRecord.setLunchAllowance(StringUtils.isEmpty(dtl.getLunchAllowance()) ? null : new BigDecimal(StringUtils.replace(dtl.getLunchAllowance(), "￥", "")));
                    salaryStatementDtlRecord.setAgeAllowance(StringUtils.isEmpty(dtl.getAgeAllowance()) ? null : new BigDecimal(StringUtils.replace(dtl.getAgeAllowance(), "￥", "")));
                    salaryStatementDtlRecord.setOvertimeDays(StringUtils.isEmpty(dtl.getOvertimeDays()) ? null : new BigDecimal(StringUtils.replace(dtl.getOvertimeDays(), "￥", "")));
                    salaryStatementDtlRecord.setBonus(StringUtils.isEmpty(dtl.getBonus()) ? null : new BigDecimal(StringUtils.replace(dtl.getBonus(), "￥", "")));
                    salaryStatementDtlRecord.setAbsenceDays(StringUtils.isEmpty(dtl.getAbsenceDays()) ? null : new BigDecimal(StringUtils.replace(dtl.getAbsenceDays(), "￥", "")));
                    salaryStatementDtlRecord.setAttendanceDeduct(StringUtils.isEmpty(dtl.getAttendanceDeduct()) ? null : new BigDecimal(StringUtils.replace(dtl.getAttendanceDeduct(), "￥", "")));
                    salaryStatementDtlRecord.setTotalWages(StringUtils.isEmpty(dtl.getTotalWages()) ? null : new BigDecimal(StringUtils.replace(dtl.getTotalWages(), "￥", "")));
                    salaryStatementDtlRecord.setAllowanceBase(StringUtils.isEmpty(dtl.getAllowanceBase()) ? null : new BigDecimal(StringUtils.replace(dtl.getAllowanceBase(), "￥", "")));
                    salaryStatementDtlRecord.setEndowmentInsPers(StringUtils.isEmpty(dtl.getEndowmentInsPers()) ? null : new BigDecimal(StringUtils.replace(dtl.getEndowmentInsPers(), "￥", "")));
                    salaryStatementDtlRecord.setUnemployInsPers(StringUtils.isEmpty(dtl.getUnemployInsPers()) ? null : new BigDecimal(StringUtils.replace(dtl.getUnemployInsPers(), "￥", "")));
                    salaryStatementDtlRecord.setMedicalInsPers(StringUtils.isEmpty(dtl.getMedicalInsPers()) ? null : new BigDecimal(StringUtils.replace(dtl.getMedicalInsPers(), "￥", "")));
                    salaryStatementDtlRecord.setHabitationInsPers(StringUtils.isEmpty(dtl.getHabitationInsPers()) ? null : new BigDecimal(StringUtils.replace(dtl.getHabitationInsPers(), "￥", "")));
                    salaryStatementDtlRecord.setEndowmentInsCom(StringUtils.isEmpty(dtl.getEndowmentInsCom()) ? null : new BigDecimal(StringUtils.replace(dtl.getEndowmentInsCom(), "￥", "")));
                    salaryStatementDtlRecord.setUnemployInsCom(StringUtils.isEmpty(dtl.getUnemployInsCom()) ? null : new BigDecimal(StringUtils.replace(dtl.getUnemployInsCom(), "￥", "")));
                    salaryStatementDtlRecord.setMedicalInsCom(StringUtils.isEmpty(dtl.getMedicalInsCom()) ? null : new BigDecimal(StringUtils.replace(dtl.getMedicalInsCom(), "￥", "")));
                    salaryStatementDtlRecord.setInjuryInsCom(StringUtils.isEmpty(dtl.getInjuryInsCom()) ? null : new BigDecimal(StringUtils.replace(dtl.getInjuryInsCom(), "￥", "")));
                    salaryStatementDtlRecord.setBirthInsCom(StringUtils.isEmpty(dtl.getBirthInsCom()) ? null : new BigDecimal(StringUtils.replace(dtl.getBirthInsCom(), "￥", "")));
                    salaryStatementDtlRecord.setHabitationInsCom(StringUtils.isEmpty(dtl.getHabitationInsCom()) ? null : new BigDecimal(StringUtils.replace(dtl.getHabitationInsCom(), "￥", "")));
                    salaryStatementDtlRecord.setIndividualIncomeTax(StringUtils.isEmpty(dtl.getIndividualIncomeTax()) ? null : new BigDecimal(StringUtils.replace(dtl.getIndividualIncomeTax(), "￥", "")));
                    salaryStatementDtlRecord.setNetWages(StringUtils.isEmpty(dtl.getNetWages()) ? null : new BigDecimal(StringUtils.replace(dtl.getNetWages(), "￥", "")));

                    salaryStatementDtlRecordList.add(salaryStatementDtlRecord);
                }
                if (!salaryStatementRecordList.isEmpty()) {
                    this.saveOrUpdateBatch(salaryStatementRecordList);
                }
                if (!salaryStatementDtlRecordList.isEmpty()) {
                    this.salaryStatementDtlRecordService.saveOrUpdateBatch(salaryStatementDtlRecordList);
                }
            }
            JSONObject fileUploadObject = FileUtils.percentageRecord(i, files.length);
            ttlContext.putValue(batchId, fileUploadObject);
            log.info("salary excel upload batchId: {} file complete: {}", batchId, fileUploadObject.getDouble("percentage"));
        }
    }

    @Override
    public void updateStatus(String batchId, String valid) {

    }

    @Override
    public IPage<SalaryStatementRecordVO> getSalaryStatementRecord(Page page, SalaryStatementRecordDTO salaryStatementRecordDTO) {
        IPage<SalaryStatementRecord> salaryStatementRecordIPage = this.baseMapper.selectSalaryStatementRecord(page, salaryStatementRecordDTO);
        List<SalaryStatementRecordVO> salaryStatementRecordVOList = salaryStatementRecordIPage.getRecords()
                .stream()
                .map(record -> {
                    SalaryStatementRecordVO salaryStatementRecordVO = new SalaryStatementRecordVO();
                    BeanUtils.copyProperties(record, salaryStatementRecordVO);
                    salaryStatementRecordVO.setAccountPeriod(DateUtils.formatDateTime(record.getAccountPeriod(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    salaryStatementRecordVO.setUpdateDate(DateUtils.formatDateTime(record.getUpdateDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    return salaryStatementRecordVO;
                }).collect(Collectors.toList());
        return CommonUtils.newIPage(salaryStatementRecordIPage, salaryStatementRecordVOList);
    }
}

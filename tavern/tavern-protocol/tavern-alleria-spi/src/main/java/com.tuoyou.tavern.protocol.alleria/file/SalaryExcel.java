package com.tuoyou.tavern.protocol.alleria.file;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/21 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SalaryExcel extends BaseRowModel {

    @ExcelProperty(index = 0)
    private String staffType;
    @ExcelProperty(index = 1)
    private String staffId;
    @ExcelProperty(index = 2)
    private String needEndowmentIns;
    @ExcelProperty(index = 3)
    private String needUnemployIns;
    @ExcelProperty(index = 4)
    private String needMedicalIns;
    @ExcelProperty(index = 5)
    private String needInjuryIns;
    @ExcelProperty(index = 6)
    private String needBirthIns;
    @ExcelProperty(index = 7)
    private String needHabitationIns;
    @ExcelProperty(index = 8)
    private String name;
    @ExcelProperty(index = 9)
    private String householdType;
    @ExcelProperty(index = 10)
    private String baseSalary;
    @ExcelProperty(index = 11)
    private String positionValue;
    @ExcelProperty(index = 12)
    private String travelAllowance;
    @ExcelProperty(index = 13)
    private String businessAllowance;
    @ExcelProperty(index = 14)
    private String telephoneAllowance;
    @ExcelProperty(index = 15)
    private String taxiAllowance;
    @ExcelProperty(index = 16)
    private String lunchAllowance;
    @ExcelProperty(index = 17)
    private String ageAllowance;
    @ExcelProperty(index = 18)
    private String overtimeDays;
    @ExcelProperty(index = 19)
    private String overtimePay;
    @ExcelProperty(index = 20)
    private String bonus;
    @ExcelProperty(index = 21)
    private String absenceDays;
    @ExcelProperty(index = 22)
    private String attendanceDeduct;
    @ExcelProperty(index = 23)
    private String totalWages;
    @ExcelProperty(index = 24)
    private String allowanceBase;
    @ExcelProperty(index = 25)
    private String endowmentInsPers;
    @ExcelProperty(index = 26)
    private String unemployInsPers;
    @ExcelProperty(index = 27)
    private String medicalInsPers;
    @ExcelProperty(index = 28)
    private String habitationInsPers;
    @ExcelProperty(index = 29)
    private String endowmentInsCom;
    @ExcelProperty(index = 30)
    private String unemployInsCom;
    @ExcelProperty(index = 31)
    private String medicalInsCom;
    @ExcelProperty(index = 32)
    private String injuryInsCom;
    @ExcelProperty(index = 33)
    private String birthInsCom;
    @ExcelProperty(index = 34)
    private String habitationInsCom;
    @ExcelProperty(index = 35)
    private String individualIncomeTax;
    @ExcelProperty(index = 38)
    private String netWages;
    @ExcelProperty(index = 41)
    private String bankCard;
}

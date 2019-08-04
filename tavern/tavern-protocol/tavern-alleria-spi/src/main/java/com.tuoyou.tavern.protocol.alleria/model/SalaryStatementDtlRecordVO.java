package com.tuoyou.tavern.protocol.alleria.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalaryStatementDtlRecordVO {
    private String detailId;

    private String fileId;

    private String staffType;

    private String staffId;

    private String needEndowmentIns;

    private String needUnemployIns;

    private String needMedicalIns;

    private String needInjuryIns;

    private String needBirthIns;

    private String needHabitationIns;

    private String name;

    private String householdType;

    private BigDecimal baseSalary;

    private BigDecimal positionValue;

    private BigDecimal travelAllowance;

    private BigDecimal businessAllowance;

    private BigDecimal telephoneAllowance;

    private BigDecimal taxiAllowance;

    private BigDecimal lunchAllowance;

    private BigDecimal ageAllowance;

    private BigDecimal overtimeDays;

    private BigDecimal overtimePay;

    private BigDecimal bonus;

    private BigDecimal absenceDays;

    private BigDecimal attendanceDeduct;

    private BigDecimal totalWages;

    private BigDecimal allowanceBase;

    private BigDecimal endowmentInsPers;

    private BigDecimal unemployInsPers;

    private BigDecimal medicalInsPers;

    private BigDecimal habitationInsPers;

    private BigDecimal endowmentInsCom;

    private BigDecimal unemployInsCom;

    private BigDecimal medicalInsCom;

    private BigDecimal injuryInsCom;

    private BigDecimal birthInsCom;

    private BigDecimal habitationInsCom;

    private BigDecimal individualIncomeTax;

    private BigDecimal netWages;

    private String bankCard;
}
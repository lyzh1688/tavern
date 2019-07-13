package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ALR_SALARY_STATEMENT_DTL_RECORD")
public class SalaryStatementDtlRecord extends Model<SalaryStatementDtlRecord> {
    @NonNull
    @TableId
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

    private Long baseSalary;

    private Long positionValue;

    private Long travelAllowance;
}
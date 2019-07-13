package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.salary.service.SalaryStatementDtlRecordService;
import com.tuoyou.tavern.alleria.salary.service.SalaryStatementRecordService;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementDtlRecord;
import com.tuoyou.tavern.protocol.alleria.model.SalaryStatementRecord;
import com.tuoyou.tavern.protocol.alleria.response.SalaryStatementRecordDtlResponse;
import com.tuoyou.tavern.protocol.alleria.response.SalaryStatementRecordResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/13 <br>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/alleria/salary")
public class SalaryStatementRecordEndpoint {
    private final SalaryStatementDtlRecordService salaryStatementDtlRecordService;
    private final SalaryStatementRecordService salaryStatementRecordService;

    /*
     * 查询工资记录
     *
     * */
    @GetMapping(value = "/record/page")
    public SalaryStatementRecordResponse querySalaryStatementRecord(Page page, SalaryStatementRecord salaryStatementRecord) {
        return new SalaryStatementRecordResponse(this.salaryStatementRecordService.page(page, Wrappers.query(salaryStatementRecord)));
    }

    /*
     * 查询工资单详情
     *
     * */
    @GetMapping(value = "/dtl/page")
    public SalaryStatementRecordDtlResponse querySalaryStatementRecordDtl(Page page, SalaryStatementDtlRecord stdInvoiceDtlRecord) {
        return new SalaryStatementRecordDtlResponse(this.salaryStatementDtlRecordService.page(page, Wrappers.query(stdInvoiceDtlRecord)));
    }


}

package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.bank.service.BankStatementDtlCcblRecordService;
import com.tuoyou.tavern.alleria.bank.service.BankStatementRecordService;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDtlQryInfo;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementRecord;
import com.tuoyou.tavern.protocol.alleria.response.BankStatementDtlResponse;
import com.tuoyou.tavern.protocol.alleria.response.BankStatementResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/11 <br>
 */
@AllArgsConstructor
@RestController
@RequestMapping("/alleria/bankStatement")
public class BankStatementEndpoint {

    private final BankStatementRecordService bankStatementRecordService;
    private final BankStatementDtlCcblRecordService bankStatementDtlCcblRecordService;

    @GetMapping("/page")
    BankStatementResponse queryBankStatementRecord(Page page, BankStatementRecord bankStatementRecord) {
        return new BankStatementResponse(this.bankStatementRecordService.getBankStatementRecord(page, bankStatementRecord));
    }

    @GetMapping("/dtl")
    BankStatementDtlResponse queryBankStatementDtlRecord(Page page, BankStatementDtlQryInfo bankStatementRecord){
        return new BankStatementDtlResponse(this.bankStatementDtlCcblRecordService.getBankStatementDtlRecord(page, bankStatementRecord));
    }
}

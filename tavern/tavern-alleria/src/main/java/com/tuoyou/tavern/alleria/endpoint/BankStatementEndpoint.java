package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.bank.service.BankStatementDtlCcblRecordService;
import com.tuoyou.tavern.alleria.bank.service.BankStatementRecordService;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDtlDTO;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementRecord;
import com.tuoyou.tavern.protocol.alleria.response.BankStatementDtlResponse;
import com.tuoyou.tavern.protocol.alleria.response.BankStatementResponse;
import com.tuoyou.tavern.protocol.alleria.response.DictResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /*
     * 查询银行流水
     *
     * */
    @GetMapping("/page")
    BankStatementResponse queryBankStatementRecord(Page page, BankStatementRecord bankStatementRecord) {
        return new BankStatementResponse(this.bankStatementRecordService.getBankStatementRecord(page, bankStatementRecord));
    }

    /*
     * 查询银行流水明细
     *
     * */
    @GetMapping("/dtl")
    BankStatementDtlResponse queryBankStatementDtlRecord(Page page, BankStatementDtlDTO bankStatementRecord) {
        return new BankStatementDtlResponse(this.bankStatementDtlCcblRecordService.getBankStatementDtlRecord(page, bankStatementRecord));
    }

    /*
     * 查询银行流水账户字典
     *
     * */
    @GetMapping("/recvAccntId")
    DictResponse queryRecvAccntIdDict(@RequestParam(name = "batchId", required = true) String batchId,
                                      @RequestParam(name = "recvAccntId") String recvAccntId) {
        return new DictResponse(this.bankStatementDtlCcblRecordService.getRecvAccntIdDict(batchId, recvAccntId));
    }
}

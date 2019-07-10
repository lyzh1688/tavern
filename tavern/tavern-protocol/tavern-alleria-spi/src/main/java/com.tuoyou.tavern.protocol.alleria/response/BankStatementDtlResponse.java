package com.tuoyou.tavern.protocol.alleria.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementDtlCcblRecord;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementRecord;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/10 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BankStatementDtlResponse extends TavernResponse {

    private IPage<BankStatementDtlCcblRecord> bankStatementDtlCcblRecordIPage;

    public BankStatementDtlResponse(IPage<BankStatementDtlCcblRecord> iPage) {
        this.bankStatementDtlCcblRecordIPage = iPage;
    }

}

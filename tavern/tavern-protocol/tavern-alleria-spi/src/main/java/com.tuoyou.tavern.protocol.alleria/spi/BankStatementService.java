package com.tuoyou.tavern.protocol.alleria.spi;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.dto.BankStatementDtlDTO;
import com.tuoyou.tavern.protocol.alleria.model.BankStatementRecord;
import com.tuoyou.tavern.protocol.alleria.response.BankStatementDtlResponse;
import com.tuoyou.tavern.protocol.alleria.response.BankStatementResponse;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/10 <br>
 */
public interface BankStatementService {

    BankStatementResponse queryBankStatementRecord(Page page, BankStatementRecord bankStatementRecord);

    BankStatementDtlResponse queryBankStatementDtlRecord(Page page, BankStatementDtlDTO bankStatementRecord);
}

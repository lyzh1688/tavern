package com.tuoyou.tavern.protocol.alleria.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
public class BankStatementResponse extends TavernResponse {

    private IPage<BankStatementRecord> bankStatementInfo;

    public BankStatementResponse(IPage<BankStatementRecord> iPage) {
        this.bankStatementInfo = iPage;
    }

}

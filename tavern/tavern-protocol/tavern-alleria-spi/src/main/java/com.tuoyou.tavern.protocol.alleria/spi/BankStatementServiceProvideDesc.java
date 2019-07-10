package com.tuoyou.tavern.protocol.alleria.spi;

import com.tuoyou.tavern.protocol.common.RequestMethods;
import com.tuoyou.tavern.protocol.common.SpiDescription;

import java.util.HashMap;
import java.util.Map;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/10 <br>
 */
public class BankStatementServiceProvideDesc {
    public static Map<Class<?>, Map<String, SpiDescription>> serviceProviderDesc = new HashMap<>();
    static {
        //BankStatementService
        Map<String,SpiDescription> bankStatementDescMap = new HashMap<>();
        SpiDescription queryBankStatementRecord = new SpiDescription.Builder()
                .serviceClass(BankStatementService.class)
                .requestMethod(RequestMethods.GET)
                .resourcePath("/alleria/bankStatement/page?current={current}&size={size}&customId={customId}&accountPeriod={accountPeriod}")
                .serviceApi("queryBankStatementRecord")
                .build();
        SpiDescription queryBankStatementDtlRecord = new SpiDescription.Builder()
                .serviceClass(BankStatementService.class)
                .requestMethod(RequestMethods.GET)
                .resourcePath("/alleria/bankStatement/dtl?current={current}&size={size}&startTradeDate={startTradeDate}&endTradeDate={endTradeDate}&recvAccntId={recvAccntId}")
                .serviceApi("queryBankStatementDtlRecord")
                .build();

        bankStatementDescMap.put("queryBankStatementRecord",queryBankStatementRecord);
        bankStatementDescMap.put("queryBankStatementDtlRecord",queryBankStatementDtlRecord);
        serviceProviderDesc.put(BankStatementService.class,bankStatementDescMap);
    }
}

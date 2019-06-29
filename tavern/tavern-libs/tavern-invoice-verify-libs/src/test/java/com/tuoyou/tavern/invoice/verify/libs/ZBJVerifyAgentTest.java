package com.tuoyou.tavern.invoice.verify.libs;

import com.alibaba.fastjson.JSONObject;
import com.tuoyou.tavern.invoice.verify.libs.client.VerifyAgent;
import com.tuoyou.tavern.invoice.verify.libs.client.ZBJVerifyAgent;
import com.tuoyou.tavern.invoice.verify.libs.model.InvoiceKeyModel;
import com.tuoyou.tavern.invoice.verify.libs.model.ZBJVerifyResult;
import com.tuoyou.tavern.invoice.verify.libs.property.ZBJInvoiceVerifyProperty;
import org.junit.Test;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
public class ZBJVerifyAgentTest {
    @Test
    public void verifyTest() throws Exception {
        ZBJInvoiceVerifyProperty zbjInvoiceVerifyProperty = new ZBJInvoiceVerifyProperty();
        zbjInvoiceVerifyProperty.setAppCode("caebcc5b40a741c1ba50cfc0fc8d9a97");
        zbjInvoiceVerifyProperty.setHost("https://fapiao.market.alicloudapi.com");
        zbjInvoiceVerifyProperty.setPath("/v2/invoice/query");
        //        TBInvoiceOCRProperty.setOcrType("2009");
        VerifyAgent<ZBJVerifyResult> zbjVerifyAgent = new ZBJVerifyAgent(zbjInvoiceVerifyProperty);

        InvoiceKeyModel keyModel = new InvoiceKeyModel();
        keyModel.setInvoiceCode("3100182130");
        keyModel.setInvoiceId("61102018");
        keyModel.setInvoiceDate("20180904");
        keyModel.setTaxFreeAmount("9422.64");


        ZBJVerifyResult zbjVerifyResult = zbjVerifyAgent.doVerify(keyModel);
        System.out.print(JSONObject.toJSONString(zbjVerifyResult));
    }
}

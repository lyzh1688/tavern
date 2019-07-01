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
//        {"不含税金额":"491.38","发票代码":"3200181130","发票号码":"49184545","开票日期":"2018年5月7日","校验码":""}
        InvoiceKeyModel keyModel = new InvoiceKeyModel();
        keyModel.setInvoiceCode("3200181130");
        keyModel.setInvoiceId("49184545");
        keyModel.setInvoiceDate("20180507");
        keyModel.setTaxFreeAmount("491.38");


        ZBJVerifyResult zbjVerifyResult = zbjVerifyAgent.doVerify(keyModel);
        System.out.print(JSONObject.toJSONString(zbjVerifyResult));
//        {"code":1003,"description":"税局要求只能查询为1年内发票","message":"参数错误：只能查询一年内的发票","success":false}D
    }
}

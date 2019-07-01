package com.tuoyou.tavern.invoice.ocr.libs;

import com.alibaba.fastjson.JSONObject;
import com.tuoyou.tavern.invoice.ocr.libs.client.OcrAgent;
import com.tuoyou.tavern.invoice.ocr.libs.client.TBOcrAgent;
import com.tuoyou.tavern.invoice.ocr.libs.model.TBInvoiceModel;
import com.tuoyou.tavern.invoice.ocr.libs.property.TBInvoiceOCRProperty;
import org.junit.Test;

/**
 * Created by 刘悦之 on 2019/6/28.
 */
public class XYOcrAgentTest {
    @Test
    public void ocrTest() throws Exception {
//        CountDownLatch latch = new CountDownLatch(1);
        TBInvoiceOCRProperty TBInvoiceOCRProperty = new TBInvoiceOCRProperty();
        TBInvoiceOCRProperty.setAppCode("caebcc5b40a741c1ba50cfc0fc8d9a97");
        TBInvoiceOCRProperty.setHost("https://ocrapi-invoice.taobao.com");
        TBInvoiceOCRProperty.setPath("/ocrservice/invoice");
//        TBInvoiceOCRProperty.setOcrType("2009");
        OcrAgent<TBInvoiceModel> TBOcrAgent = new TBOcrAgent(TBInvoiceOCRProperty);
        TBInvoiceModel tbInvoiceModel = TBOcrAgent.doOcr("D:\\项目\\财务系统\\扫描\\2018_09_19_14_12_18.jpg");
        System.out.print(JSONObject.toJSONString(tbInvoiceModel));
//        {"不含税金额":"491.38","发票代码":"3200181130","发票号码":"49184545","开票日期":"2018年5月7日","校验码":""}
     }
}

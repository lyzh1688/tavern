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
        TBInvoiceModel tbInvoiceModel = TBOcrAgent.doOcr("D:\\TuoYou-Doc\\invoice_well\\2018_09_19_14_24_45.jpg");
        System.out.print(JSONObject.toJSONString(tbInvoiceModel));
     }
}

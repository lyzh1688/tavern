package com.tuoyou.tavern.alleria.configuration;

import com.tuoyou.tavern.invoice.ocr.libs.client.TBOcrAgent;
import com.tuoyou.tavern.invoice.verify.libs.client.ZBJVerifyAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
@Configuration
public class InvoiceConfiguration {
    @Autowired
    ZBJVerifyAgent verifyAgent;

    @Autowired
    TBOcrAgent ocrAgent;

}

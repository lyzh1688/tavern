package com.tuoyou.tavern.invoice.starter;

import com.tuoyou.tavern.invoice.ocr.libs.client.TBOcrAgent;
import com.tuoyou.tavern.invoice.ocr.libs.property.TBInvoiceOCRProperty;
import com.tuoyou.tavern.invoice.verify.libs.client.ZBJVerifyAgent;
import com.tuoyou.tavern.invoice.verify.libs.property.ZBJInvoiceVerifyProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
@Configuration
public class InvoiceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "tuoyou.tavern.invoice.verify.zbj")
    public ZBJInvoiceVerifyProperty zbjInvoiceVerifyProperty(){
        return new ZBJInvoiceVerifyProperty();
    }

    @Bean
    @ConfigurationProperties(prefix = "tuoyou.tavern.invoice.ocr.tb")
    public TBInvoiceOCRProperty tbInvoiceOCRProperty(){
        return new TBInvoiceOCRProperty();
    }

    @Bean
    public ZBJVerifyAgent zbjVerifyAgent(ZBJInvoiceVerifyProperty zbjInvoiceVerifyProperty){
        return new ZBJVerifyAgent(zbjInvoiceVerifyProperty);
    }

    @Bean
    public TBOcrAgent tbOcrAgent(TBInvoiceOCRProperty tbInvoiceOCRProperty){
        return new TBOcrAgent(tbInvoiceOCRProperty);
    }
}

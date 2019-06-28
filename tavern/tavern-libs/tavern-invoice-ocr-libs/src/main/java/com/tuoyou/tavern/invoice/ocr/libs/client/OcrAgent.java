package com.tuoyou.tavern.invoice.ocr.libs.client;

import com.alibaba.fastjson.JSONObject;
import com.tuoyou.tavern.invoice.ocr.libs.model.TBInvoiceModel;
import com.tuoyou.tavern.invoice.ocr.libs.property.InvoiceOCRProperty;
import com.tuoyou.tavern.invoice.ocr.libs.utils.HttpUtils;
import com.tuoyou.tavern.invoice.ocr.libs.utils.ImageUtils;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘悦之 on 2019/6/28.
 */
public abstract class OcrAgent<T> {

    InvoiceOCRProperty invoiceOCRProperty;

    public OcrAgent(InvoiceOCRProperty property) {
        this.invoiceOCRProperty = property;
    }

    public T doOcr(String file) throws Exception{
        HttpResponse response = this.call(ImageUtils.imageToBase64ByLocal(file));
        return this.parseResponse(response);
    }

    protected HttpResponse call(String image) throws Exception {
        Map<String, String> headers = createRequestHeader(this.invoiceOCRProperty);
        String body = this.createRequestBody(this.invoiceOCRProperty, image).toJSONString();
        HttpResponse response = HttpUtils.doPost(this.invoiceOCRProperty.getHost(), this.invoiceOCRProperty.getPath(), "POST", headers, new HashMap<String, String>(), body);
        return response;
    }

    protected abstract Map<String, String> createRequestHeader(InvoiceOCRProperty invoiceOCRProperty);

    protected abstract JSONObject createRequestBody(InvoiceOCRProperty invoiceOCRProperty, String image);

    protected abstract T parseResponse(HttpResponse response) throws IOException;

}

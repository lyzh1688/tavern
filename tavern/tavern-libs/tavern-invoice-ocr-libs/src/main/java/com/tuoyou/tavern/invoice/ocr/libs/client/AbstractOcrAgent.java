package com.tuoyou.tavern.invoice.ocr.libs.client;

import com.alibaba.fastjson.JSONObject;
import com.tuoyou.tavern.invoice.common.libs.ServiceProperty;
import com.tuoyou.tavern.invoice.common.libs.utils.HttpUtils;
import com.tuoyou.tavern.invoice.common.libs.utils.ImageUtils;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘悦之 on 2019/6/28.
 */
public abstract class AbstractOcrAgent<T> implements OcrAgent<T>{

    ServiceProperty invoiceOCRProperty;

    public AbstractOcrAgent(ServiceProperty property) {
        this.invoiceOCRProperty = property;
    }

    @Override
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

    protected abstract Map<String, String> createRequestHeader(ServiceProperty invoiceOCRProperty);

    protected abstract JSONObject createRequestBody(ServiceProperty invoiceOCRProperty, String image);

    protected abstract T parseResponse(HttpResponse response) throws IOException;

}

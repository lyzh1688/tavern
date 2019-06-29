package com.tuoyou.tavern.invoice.verify.libs.client;

import com.alibaba.fastjson.JSONObject;
import com.tuoyou.tavern.invoice.common.libs.ServiceProperty;
import com.tuoyou.tavern.invoice.common.libs.utils.HttpUtils;
import com.tuoyou.tavern.invoice.common.libs.utils.ImageUtils;
import com.tuoyou.tavern.invoice.verify.libs.model.InvoiceKeyModel;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
public abstract class AbstractVerifyAgent<T> implements VerifyAgent<T> {

    ServiceProperty serviceProperty;

    public AbstractVerifyAgent(ServiceProperty serviceProperty) {
        this.serviceProperty = serviceProperty;
    }

    @Override
    public T doVerify(InvoiceKeyModel keyModel) throws Exception {
        HttpResponse response = this.call(keyModel);
        return this.parseResponse(response);
    }

    protected HttpResponse call(InvoiceKeyModel keyModel) throws Exception {
        Map<String, String> headers = createRequestHeader(this.serviceProperty);
        Map<String, String> body = this.createRequestBody(this.serviceProperty, keyModel);
        HttpResponse response = HttpUtils.doPost(this.serviceProperty.getHost(), this.serviceProperty.getPath(), "POST", headers, new HashMap<String, String>(), body);
        return response;
    }

    protected abstract Map<String, String> createRequestHeader(ServiceProperty serviceProperty);

    protected abstract Map<String, String> createRequestBody(ServiceProperty serviceProperty, InvoiceKeyModel keyModel);

    protected abstract T parseResponse(HttpResponse response) throws IOException;
}

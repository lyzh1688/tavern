package com.tuoyou.tavern.invoice.ocr.libs.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuoyou.tavern.invoice.ocr.libs.model.TBInvoiceModel;
import com.tuoyou.tavern.invoice.ocr.libs.property.InvoiceOCRProperty;
import com.tuoyou.tavern.invoice.ocr.libs.property.TBInvoiceOCRProperty;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘悦之 on 2019/6/28.
 */
public class TBOcrAgent extends AbstractOcrAgent<TBInvoiceModel> {

    private static String authorizationPrefix = "APPCODE ";

    public TBOcrAgent(TBInvoiceOCRProperty property) {
        super(property);
    }

    @Override
    protected Map<String, String> createRequestHeader(InvoiceOCRProperty invoiceOCRProperty) {
        TBInvoiceOCRProperty TBInvoiceOCRProperty = (TBInvoiceOCRProperty) invoiceOCRProperty;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", authorizationPrefix + TBInvoiceOCRProperty.getAppCode());
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        return headers;
    }

    @Override
    protected JSONObject createRequestBody(InvoiceOCRProperty invoiceOCRProperty, String image) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("img", image);
        return jsonObj;
    }

    @Override
    protected TBInvoiceModel parseResponse(HttpResponse response) throws IOException {
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject jsonObject = JSON.parseObject(result);
        JSONObject data = jsonObject.getJSONObject("data");
        TBInvoiceModel invoiceModel = JSON.toJavaObject(data, TBInvoiceModel.class);
        return invoiceModel;
    }
}

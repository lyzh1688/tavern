package com.tuoyou.tavern.invoice.verify.libs.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.tuoyou.tavern.invoice.common.libs.ServiceProperty;
import com.tuoyou.tavern.invoice.verify.libs.model.InvoiceKeyModel;
import com.tuoyou.tavern.invoice.verify.libs.model.ZBJVerifyResult;
import com.tuoyou.tavern.invoice.verify.libs.property.ZBJInvoiceVerifyProperty;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
public class ZBJVerifyAgent extends AbstractVerifyAgent<ZBJVerifyResult> {

    private static String authorizationPrefix = "APPCODE ";

    public ZBJVerifyAgent(ServiceProperty serviceProperty) {
        super(serviceProperty);
    }

    @Override
    protected Map<String, String> createRequestHeader(ServiceProperty serviceProperty) {
        ZBJInvoiceVerifyProperty zbjInvoiceVerifyProperty = (ZBJInvoiceVerifyProperty) serviceProperty;
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", authorizationPrefix + zbjInvoiceVerifyProperty.getAppCode());
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        return headers;
    }

    @Override
    protected Map<String, String> createRequestBody(ServiceProperty serviceProperty, InvoiceKeyModel keyModel) {
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("checkCode", Strings.nullToEmpty(keyModel.getCheckCode()));
        bodyMap.put("fpdm",keyModel.getInvoiceCode());
        bodyMap.put("fphm",keyModel.getInvoiceId());
        bodyMap.put("kprq",keyModel.getInvoiceDate());
        bodyMap.put("noTaxAmount",Strings.nullToEmpty(keyModel.getTaxFreeAmount()));
        return bodyMap;
    }

    @Override
    protected ZBJVerifyResult parseResponse(HttpResponse response) throws IOException {
        String result = EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject jsonObject = JSON.parseObject(result);
        ZBJVerifyResult zbjVerifyResult = JSON.toJavaObject(jsonObject, ZBJVerifyResult.class);
        return zbjVerifyResult;
    }
}

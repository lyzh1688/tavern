package com.tuoyou.tavern.rpc.libs.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuoyou.tavern.protocol.common.RequestMethods;
import com.tuoyou.tavern.protocol.common.RetCode;
import com.tuoyou.tavern.protocol.common.SpiDescription;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyuezhi on 2017/6/22.
 */
public class ProxyHandler<T> implements InvocationHandler, ResourceParser {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<T> serviceProvider;

    private Map<String, SpiDescription> spiDescriptions;

    public ProxyHandler(Class<T> serviceProvider, Map<String, SpiDescription> spiDescriptions) {
        this.serviceProvider = serviceProvider;
        this.spiDescriptions = spiDescriptions;
    }

    protected String getUri() {
        return "";
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String url = this.getUri();
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json; charset=UTF-8");
        Map<String, String> query = new HashMap<>();
        Class<? extends TavernResponse> retType = (Class<? extends TavernResponse>) method.getReturnType();
        try {
            SpiDescription spiDescription = spiDescriptions.get(method.getName());
            String uriPath = this.parseUri(spiDescription.getResourcePath(), method, args);
            String requestBodyStr = "";
            HttpResponse httpResponse = null;
            switch (spiDescription.getRequestMethod()) {
                case RequestMethods.GET:
                    httpResponse = HttpUtils.doGet(url, uriPath, header, query);
                    break;
                case RequestMethods.DELETE:
                    httpResponse = HttpUtils.doDelete(url, uriPath, header, query);
                    break;
                case RequestMethods.PUT:
                    requestBodyStr = this.parseRequestBody(method,args);
                    httpResponse = HttpUtils.doPut(url, uriPath, header, query,requestBodyStr);
                    break;
                case RequestMethods.POST:
                    requestBodyStr = this.parseRequestBody(method,args);
                    httpResponse = HttpUtils.doPost(url, uriPath, header, query,requestBodyStr);
                    break;
            }
            String retObjectString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            return JSON.toJavaObject(JSONObject.parseObject(retObjectString), retType);
        } catch (Exception e) {
            logger.error(e.getMessage());
            TavernResponse response = retType.newInstance();
            response.setRetCode(RetCode.SYS_ERROR);
        }

        TavernResponse response = retType.newInstance();
        response.setRetCode(RetCode.UN_SUPPORTED_METHOD);
        return response;
    }
}

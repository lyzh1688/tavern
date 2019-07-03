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
public class ProxyHandler<T> implements InvocationHandler{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<T> serviceProvider;

    private Map<String,SpiDescription> spiDescriptions;

    public ProxyHandler(Class<T> serviceProvider,Map<String,SpiDescription> spiDescriptions) {
        this.serviceProvider = serviceProvider;
        this.spiDescriptions = spiDescriptions;
    }

    protected String getUri(){
        return "";
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String url = this.getUri();
        Map<String,String> header = new HashMap<>();
        header.put("Content-Type", "application/json; charset=UTF-8");
        Map<String,String> query = new HashMap<>();
        try{
            SpiDescription spiDescription = spiDescriptions.get(method.getName());
            String uriPath = ResourcePathParser.parse(spiDescription.getResourcePath(),method,args);
            Class<?> retType = method.getReturnType();
            switch (spiDescription.getRequestMethod()){
                case RequestMethods.GET:
                    HttpResponse httpResponse = HttpUtils.doGet(url, uriPath, header, query);
                    String retObjectString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                    return JSON.toJavaObject(JSONObject.parseObject(retObjectString),retType);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
            TavernResponse response = new TavernResponse();
            response.setRetCode(RetCode.SYS_ERROR);
        }

        TavernResponse response = new TavernResponse();
        response.setRetCode(RetCode.SYS_ERROR);
        return response;
    }
}

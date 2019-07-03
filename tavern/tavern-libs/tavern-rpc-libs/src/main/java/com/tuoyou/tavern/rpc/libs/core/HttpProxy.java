package com.tuoyou.tavern.rpc.libs.core;

import com.tuoyou.tavern.protocol.common.SpiDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Created by liuyuezhi on 2017/6/22.
 */
public class HttpProxy<T> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class<T> serviceProvider;

    Map<String, SpiDescription> spiDescriptionMap;

    String uri;

    private T proxy;

    public HttpProxy(String uri, Class<T> serviceProvider, Map<String, SpiDescription> spiDescriptionMap) {
        this.uri = uri;
        this.serviceProvider = serviceProvider;
        this.spiDescriptionMap = spiDescriptionMap;
        this.proxy = this.setProxy();
    }

    public T getProxy() {
        return proxy;
    }

    public T setProxy() {
        try {
            URIProxyHandler<T> handler = new URIProxyHandler<T>(this.uri, serviceProvider, this.spiDescriptionMap);
            T proxy = (T) Proxy.newProxyInstance(
                    this.getClass().getClassLoader(),
                    new Class[]{serviceProvider},
                    handler);
            return proxy;
        }catch (Exception e){
            logger.error(e.getMessage());
//            e.printStackTrace();
        }
        return null;
    }
}

package com.tuoyou.tavern.rpc.libs.core;

import com.tuoyou.tavern.protocol.common.SpiDescription;

import java.util.Map;

/**
 * Created by 刘悦之 on 2019/7/1.
 */
public class URIProxyHandler<T> extends ProxyHandler<T>{

    String uri;

    public URIProxyHandler(String uri,Class<T> serviceProvider, Map<String,SpiDescription> spiDescriptions) {
        super(serviceProvider, spiDescriptions);
        this.uri = uri;
    }

    @Override
    protected String getUri(){
        return this.uri;
    }
}

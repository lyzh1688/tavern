package com.tuoyou.tavern.protocol.common;

/**
 * Created by 刘悦之 on 2019/7/1.
 */
public class SpiDescription {
    Class<?> serviceClass;
    String serviceApi;
    String requestMethod;
    String resourcePath;
    Object requestBody;

    private SpiDescription(Builder builder){
        this.serviceClass = builder.serviceClass;
        this.serviceApi = builder.serviceApi;
        this.requestMethod = builder.requestMethod;
        this.resourcePath = builder.resourcePath;
        this.requestBody = builder.requestBody;
    }

    public Class<?> getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class<?> serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getServiceApi() {
        return serviceApi;
    }

    public void setServiceApi(String serviceApi) {
        this.serviceApi = serviceApi;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    public static class Builder{

        Class<?> serviceClass;
        String serviceApi;
        String requestMethod;
        String resourcePath;
        Object requestBody;

        public Builder serviceClass(Class<?> serviceClass){
            this.serviceClass = serviceClass;
            return this;
        }

        public Builder serviceApi(String serviceApi){
            this.serviceApi = serviceApi;
            return this;
        }

        public Builder requestMethod(String requestMethod){
            this.requestMethod = requestMethod;
            return this;
        }

        public Builder resourcePath(String resourcePath){
            this.resourcePath = resourcePath;
            return this;
        }

        public Builder requestBody(String requestBody){
            this.requestBody = requestBody;
            return this;
        }

        public SpiDescription build(){
            return new SpiDescription(this);
        }
    }
}

package com.tuoyou.tavern.invoice.ocr.libs.property;

/**
 * Created by 刘悦之 on 2019/6/28.
 */
public class InvoiceOCRProperty {

    String host;
    String path;
    String appCode;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

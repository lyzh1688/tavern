package com.tuoyou.tavern.protocol.common;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
public class TavernResponse {
    int retCode;
    String retMessage;

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }
}

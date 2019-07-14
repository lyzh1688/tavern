package com.tuoyou.tavern.protocol.authcenter.model;

import com.tuoyou.tavern.protocol.common.TavernResponse;

/**
 * Created by 刘悦之 on 2019/7/7.
 */
public class LoginResponse extends TavernResponse {
    private Login data;

    public Login getData() {
        return data;
    }

    public void setData(Login data) {
        this.data = data;
    }
}

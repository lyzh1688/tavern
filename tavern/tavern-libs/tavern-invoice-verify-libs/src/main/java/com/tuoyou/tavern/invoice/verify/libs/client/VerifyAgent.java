package com.tuoyou.tavern.invoice.verify.libs.client;

import com.tuoyou.tavern.invoice.verify.libs.model.InvoiceKeyModel;

import java.io.IOException;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
public interface VerifyAgent<T> {
    T doVerify(InvoiceKeyModel keyModel) throws Exception;
}

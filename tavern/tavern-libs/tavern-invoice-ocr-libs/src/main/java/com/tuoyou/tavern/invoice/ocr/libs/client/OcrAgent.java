package com.tuoyou.tavern.invoice.ocr.libs.client;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
public interface OcrAgent<T> {
    T doOcr(String file) throws Exception;
}

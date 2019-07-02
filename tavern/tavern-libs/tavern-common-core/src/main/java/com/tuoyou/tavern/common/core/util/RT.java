package com.tuoyou.tavern.common.core.util;

import com.tuoyou.tavern.common.core.constant.CommonConstant;

import java.io.Serializable;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/02 <br>
 */
public class RT<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code = CommonConstant.SUCCESS;

    private String msg = "success";


    private T data;

    public RT() {
        super();
    }

    public RT(T data) {
        super();
        this.data = data;
    }

    public RT(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public RT(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = CommonConstant.FAIL;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
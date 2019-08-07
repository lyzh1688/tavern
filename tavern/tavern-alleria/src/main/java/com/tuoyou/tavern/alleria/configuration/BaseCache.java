package com.tuoyou.tavern.alleria.configuration;

import java.io.Serializable;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/02 <br>
 */
public class BaseCache implements Serializable {

    private static final long serialVersionUID = 7639803472657124880L;

    private Object value;//值

    private  long gmtModify;//保存时间戳

    private int expire; //有效时间

    public BaseCache(Object value, long l, int expireTime) {
        this.value=value;
        this.gmtModify=l;
        this.expire=expireTime;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(long gmtModify) {
        this.gmtModify = gmtModify;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}

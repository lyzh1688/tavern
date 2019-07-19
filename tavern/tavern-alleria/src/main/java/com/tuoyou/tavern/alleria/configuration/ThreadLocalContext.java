package com.tuoyou.tavern.alleria.configuration;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/20 <br>
 */
public class ThreadLocalContext {

    private final ThreadLocal<Integer> THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();


    /**
     * TTL 设置租户ID
     *
     * @param tenantId
     */
    public void setTenantId(Integer tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
    }

    /**
     * 获取TTL中的租户ID
     *
     * @return
     */
    public Integer getTenantId() {
        return THREAD_LOCAL_TENANT.get();
    }

    public void clear() {
        THREAD_LOCAL_TENANT.remove();
    }

}

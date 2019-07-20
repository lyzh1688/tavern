package com.tuoyou.tavern.alleria.configuration;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/20 <br>
 */
@Slf4j
@Component
public class TTLContext {


    private static final int corePoolSize = 5;

    private static final int maximumPoolSize = 100;

    private static final int keepAliveTime = 2;

    /**
     * 默认缓存时间
     */
    private static int DEFAULT_EXPIRE_TIME = 60 * 60;
    /**
     * 默认缓存容量
     */
    private static int DEFAULT_CAPACITY = 512;
    /**
     * 最大容量
     */
    private static int MAX_CAPACITY = 10000;
    /**
     * 刷新缓存的频率
     */
    private static int MONITOR_DURATION = 2;
    /**
     * 阻塞队列
     */
    private static LinkedBlockingQueue queue = new LinkedBlockingQueue();

    /***
     * 线程池
     * @return
     */
    private static ThreadPoolExecutor executor() {
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                queue,
                new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build());
    }

    /**
     * 使用默认容器创建一个map
     */
    private static ConcurrentHashMap<String, BaseCache> cache = new ConcurrentHashMap<String, BaseCache>(DEFAULT_CAPACITY);

    //启动监控线程
    public void start() {
        executor().execute(new TimeoutTimerThread());
    }

    public boolean putValue(String key, Object value) {
        return this.putValue(key, value, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 将key-value 保存到本地缓存并制定该缓存的过期时间
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean putValue(String key, Object value, int expireTime) {
        return putCloneValue(key, value, expireTime);
    }

    /**
     * 将值通过序列化clone，处理后保存到缓存中，可以解决值引用问题
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    private boolean putCloneValue(String key, Object value, int expireTime) {
        try {
            if (cache.size() >= MAX_CAPACITY) {
                return false;
            }
            //序列化赋值
            BaseCache entity = clone(new BaseCache(value, System.nanoTime(), expireTime));
            cache.put(key, entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 序列化
     *
     * @param object
     * @param <T>
     * @return
     */
    private <T extends Serializable> T clone(T object) {
        T cloneObject = null;
        try {
//            创建默认大小为32字节的缓冲区
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            cloneObject = (T) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cloneObject;
    }

    /**
     * 从缓存取值
     *
     * @param key
     * @return
     */
    public Object getValue(String key) {
        return cache.get(key).getValue();
    }
    /**
     *
     * @param key
     * @return
     */
    public Boolean containKey(String key) {
        return cache.contains(key);
    }


    /**
     * 过期处理线程
     */
    static class TimeoutTimerThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(MONITOR_DURATION);
                    checkTime();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        /**
         * 过期缓存具体处理方法
         */
        private void checkTime() {
            cache.entrySet()
                    .stream()
                    .forEach(entry -> {
                        String key = entry.getKey();
                        BaseCache tce = cache.get(key);
                        long timeoutTime = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - tce.getGmtModify());
                        //过期时间：+timeoutTime
                        if (tce.getExpire() < timeoutTime) {
                            log.info("清除过期缓存,key: {}", key);
                            //清除过期缓存和删除对应的缓存队列
                            cache.remove(key);
                        }
                    });
        }
    }

}

package com.yuandong.db.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池管理类
 * Created by yuandong on 2017/10/16.
 */

public class ExecutorManager {

    private ExecutorService service;

    private ExecutorManager(){
        service= Executors.newFixedThreadPool(5);
    }

    private static class NestClass {
        private static ExecutorManager instance = new ExecutorManager();
    }

    public static ExecutorManager getInstance() {
        return NestClass.instance;
    }

    /**
     * 开启线程，执行runnable
     * @param runnable
     */
    public void executor(Runnable runnable){
        service.execute(runnable);
    }

    /**
     * 关闭线程池
     */
    public void shutDown(){
        if(service!=null){
            service.shutdownNow();
        }
    }


}

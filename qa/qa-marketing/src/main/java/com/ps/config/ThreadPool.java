package com.ps.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 26498
 */

@Configuration
public class ThreadPool {


    @Value("${threadPool.corePoolSize}")
    private int corePoolSize;

    @Value("${threadPool.maximumPoolSize}")
    private int maximumPoolSize;

    @Bean
    public ExecutorService getExecutorService(){
        return new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }



}
package com.mts.rocketmqconsumer.config;

import cn.hippo4j.starter.core.DynamicThreadPool;
import cn.hippo4j.starter.toolkit.thread.ThreadPoolBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @date: Create in 2022/1/7 3:07 下午
 * @auther: mts
 */
@Configuration
public class ThreadPoolConfig {


    public static final String MESSAGE_CONSUME = "rocketmq-consume";

    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor dynamicThreadPoolExecutor(){
        return ThreadPoolBuilder.builder().corePoolSize(5).maxPoolNum(10).workQueue(new LinkedBlockingQueue(100)).threadFactory(MESSAGE_CONSUME).dynamicPool().build();
    }
}

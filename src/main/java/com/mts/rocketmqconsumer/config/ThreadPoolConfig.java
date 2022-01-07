package com.mts.rocketmqconsumer.config;

import cn.hippo4j.starter.toolkit.thread.ThreadPoolBuilder;
import cn.hippo4j.starter.wrapper.DynamicThreadPoolWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @date: Create in 2022/1/7 3:07 下午
 * @auther: mts
 */
@Configuration
public class ThreadPoolConfig {

    public static final String MESSAGE_PRODUCE = "message-produce";

    public static final String MESSAGE_CONSUME = "message-consume";

    @Bean
    public DynamicThreadPoolWrapper messageCenterDynamicThreadPool(){
        return new DynamicThreadPoolWrapper(MESSAGE_CONSUME);
    }

    @Bean
    public ThreadPoolExecutor dynamicThreadPoolExecutor(){
        return ThreadPoolBuilder.builder().threadFactory(MESSAGE_PRODUCE).dynamicPool().build();
    }
}

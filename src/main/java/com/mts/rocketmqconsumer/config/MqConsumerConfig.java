package com.mts.rocketmqconsumer.config;

import com.mts.rocketmqconsumer.listener.MQMsgListenerProcessor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @date: Create in 2022/1/7 3:36 下午
 * @auther: mts
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rocketmq.consumer")
@Slf4j
public class MqConsumerConfig {

    private String groupName;
    private String namesrvAddr;
    // 消费者线程数据量
    private Integer consumeThreadMin;
    private Integer consumeThreadMax;

    @Autowired
    private MQMsgListenerProcessor mqMsgListenerProcessorm;

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumerGroup(groupName);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeThreadMin(consumeThreadMin);
        // 设置监听
        consumer.registerMessageListener(mqMsgListenerProcessorm);
        /**
         * 设置consumer第一次启动是从队列头部开始还是队列尾部开始
         * 如果不是第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        /**
         * 设置消费模型,集群还是广播,默认为集群
         */
        consumer.setMessageModel(MessageModel.CLUSTERING);

        // 设置该消费者订阅的主题和tag，如果订阅该主题下的所有tag，则使用*,
        consumer.subscribe("myTopic1","*");
        consumer.subscribe("myTopic2","tag2");

        consumer.start();
        return consumer;
    }
}

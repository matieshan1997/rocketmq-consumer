package com.mts.rocketmqconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @date: Create in 2021/9/27 11:45 上午
 * @auther: mts
 */
@Component
@Slf4j
public class MQMsgListenerProcessor implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        MessageExt msg = messageExtList.get(0);
        String body = new String(msg.getBody());
        String topic = msg.getTopic();
        String tags = msg.getTags();
        String keys = msg.getKeys();
        log.info("MQ消息topic={},tags={},key={},消息内容={}", topic,tags,keys,body);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}

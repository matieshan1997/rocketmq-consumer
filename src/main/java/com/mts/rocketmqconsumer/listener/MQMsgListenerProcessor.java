package com.mts.rocketmqconsumer.listener;

import com.mts.rocketmqconsumer.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description: 1
 * @date: Create in 2021/9/27 11:45 上午
 * @auther: mts
 */
@Component
@Slf4j
public class MQMsgListenerProcessor implements MessageListenerConcurrently {

    @Autowired
    private ThreadPoolExecutor poolExecutor;


    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        MessageExt msg = messageExtList.get(0);
        String body = new String(msg.getBody());
        String topic = msg.getTopic();
        String tags = msg.getTags();
        String keys = msg.getKeys();
        log.info("MQ消息topic={},tags={},key={},消息内容={}", topic,tags,keys,body);
        poolExecutor.execute(()->{
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = new Message();
            copy(msg,message);
            log.info("message:{}",message);
        });
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    private static void copy(MessageExt messageExt, Message message) {
        message.setTopic(messageExt.getTopic());
        message.setTags(messageExt.getTags());
        message.setKeys(messageExt.getKeys());
        message.setBody(new String(messageExt.getBody()));
    }

}

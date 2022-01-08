package com.mts.rocketmqconsumer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @date: Create in 2022/1/7 4:53 下午
 * @auther: mts
 */
@Getter
@Setter
public class Message {

    private String topic;

    private String tags;

    private String keys;

    private String body;

    @Override
    public String toString() {
        return "Message{" +
                "topic='" + topic + '\'' +
                ", tags='" + tags + '\'' +
                ", keys='" + keys + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

package com.jf.wj.demo.rabbitmqpluginsdelayed.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 15:32
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@Getter
@Setter
@Data
public class QueueMessage implements Serializable {
    private String exchange;//交换机

    private String queueName;//队列

    private Integer type;//类型 ， 主要区分普通消息和延迟消息 ，

    private Integer group;//分组

    private Date timestamp;//时间戳

    private String message;//消息内容

    private Integer status;//状态

    private int retry = 0; //重试次数

    private int maxRetry = 10;//最大次是

    private int seconds = 1;//延迟的秒数

    public QueueMessage(String queueHelloName, String message) {
        this.queueName = queueHelloName;
        this.message = message;
    }
}

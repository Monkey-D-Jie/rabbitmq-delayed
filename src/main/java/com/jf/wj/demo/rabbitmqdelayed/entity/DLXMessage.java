package com.jf.wj.demo.rabbitmqdelayed.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 14:30
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */
@Getter
@Setter
public class DLXMessage implements Serializable {

    private static final long serialVersionUID = 9956432152666L;

    public DLXMessage() {
        super();
    }

    public DLXMessage(String queueName, String content, long times) {
        super();
        this.queueName = queueName;
        this.content = content;
        this.times = times;
    }

    public DLXMessage(String exchange, String queueName, String content, long times) {
        super();
        this.exchange = exchange;
        this.queueName = queueName;
        this.content = content;
        this.times = times;
    }


    private String exchange;

    private String queueName;

    private String content;

    private long times;

}

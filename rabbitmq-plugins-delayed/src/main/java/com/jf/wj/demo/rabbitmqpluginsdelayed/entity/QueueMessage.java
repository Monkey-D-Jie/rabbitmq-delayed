package com.jf.wj.demo.rabbitmqpluginsdelayed.entity;

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
public class QueueMessage implements Serializable {
    //交换机
    private String exchange;
    //队列
    private String queueName;
    //类型 ， 主要区分普通消息和延迟消息 ，
    private Integer type;
    //分组
    private Integer group;
    //时间戳
    private Date timestamp;
    //消息内容
    private String message;
    //状态
    private Integer status;
    //重试次数
    private int retry = 0;
    //最大次是
    private int maxRetry = 10;
    //延迟的秒数
    private int seconds = 1;

    //这里需要把默认的构造函数加上，
    //使用Lombok的@Data注解，在json转换的时候，虽然不报错。但是部分关键字段
    //会出现转换失败的情况。
    //显示声明了默认构造函数，才能正确的转换出对应的实体对象来
    public QueueMessage(){

    }

    public QueueMessage(String queueHelloName, String message) {
        this.queueName = queueHelloName;
        this.message = message;
    }
}

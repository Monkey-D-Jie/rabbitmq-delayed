package com.jf.wj.demo.rabbitmqpluginsdelayed.common;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 15:26
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class MessageQueueConstants {
    /**
     * 默认即时消息交换机名称
     */
    public static final String DEFAULT_DIRECT_EXCHANGE_NAME = "plugins-default.direct.exchange";


    /**
     * 默认延迟交换机
     */
    public static final String DEFAULT_DELAYED_EXCHANGE = "plugins-default.delayed.exchange";

    /**
     * 默认延迟消息类型
     */
    public static final String DEFAULT_DELAYED_TYPE_NAME= "x-delayed-message";


    /**
     * 默认作为延时消息转发的接收队列名称
     */
    public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "plugins-default.repeat.trade.queue";


    /**
     * hello消息队列名称
     */
    public static final String QUEUE_HELLO_NAME = "plugins-app.queue.hello";
}

package com.jf.wj.demo.rabbitmqdelayed.common;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 13:52
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class MQConstant {
    private MQConstant(){
    }

    //exchange name
    public static final String DEFAULT_EXCHANGE = "KSHOP";

    //DLX QUEUE
    public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "kshop.dead.letter.queue";

    //DLX repeat QUEUE 死信转发队列
    public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "kshop.repeat.trade.queue";


    //Hello 测试消息队列名称
    public static final String HELLO_QUEUE_NAME = "HELLO";

}

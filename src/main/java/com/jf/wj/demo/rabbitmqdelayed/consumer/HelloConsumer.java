package com.jf.wj.demo.rabbitmqdelayed.consumer;

import com.jf.wj.demo.rabbitmqdelayed.common.MQConstant;
import com.jf.wj.demo.rabbitmqdelayed.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2020-10-23 13:55
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

@Component
@RabbitListener(queues = MQConstant.HELLO_QUEUE_NAME)
@Slf4j
public class HelloConsumer {
    @RabbitHandler
    public void process(String content) {
        log.info("在{}监听到了从队列{}中发来的消息---->>>{}", DateUtil.convert(DateUtil.DATE_FORMAT,new Date()) ,MQConstant.HELLO_QUEUE_NAME, content);
    }
}
